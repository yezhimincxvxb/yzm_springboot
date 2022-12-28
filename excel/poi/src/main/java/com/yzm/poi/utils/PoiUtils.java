package com.yzm.poi.utils;

import com.yzm.poi.entuty.User;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PoiUtils {

    private static final int POSITION_ROW = 0;
    private static final int POSITION_COL = 0;
    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    public static <T> SXSSFWorkbook excelExport(List<User> users, Class<T> cls) {
        int rowStart = POSITION_ROW;
        int colStart = POSITION_COL;

        // SXSSF工作簿
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        // 实体类属性过滤没有使用注解的并排序
        Field[] fields = cls.getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields)
                .filter(field -> {
                    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    if (annotation != null && annotation.col() > 0) {
                        field.setAccessible(true);
                        return true;
                    }
                    return false;
                }).sorted(Comparator.comparing(field -> {
                    int col = 0;
                    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    if (annotation != null) {
                        col = annotation.col();
                    }
                    return col;
                })).collect(Collectors.toList());

        // 设置表头
        Row head = sheet.createRow(rowStart++);
        CellStyle headStyle = getHeadCellStyle(workbook);
        AtomicInteger aj = new AtomicInteger(colStart);
        fieldList.forEach(field -> {
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null) {
                String columnName = annotation.value();
                int widths = annotation.colWidths();
                sheet.setColumnWidth(aj.get(), widths);
                addCellWithStyle(head, aj.getAndIncrement(), headStyle).setCellValue(columnName);
            }
        });

        // 设置表内容
        CellStyle bodyStyle = getBodyCellStyle(workbook);
        for (User user : users) {
            aj.set(colStart);
            Row row = sheet.createRow(rowStart++);
            fieldList.forEach(field -> {
                try {
                    Object obj = field.get(user);
                    addCellWithStyle(row, aj.getAndIncrement(), bodyStyle).setCellValue(obj.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return workbook;
    }

    private static Cell addCellWithStyle(Row row, int colPosition, CellStyle cellStyle) {
        Cell cell = row.createCell(colPosition);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    private static CellStyle getHeadCellStyle(Workbook workbook) {
        CellStyle style = getBaseCellStyle(workbook);

        // fill 填充颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private static CellStyle getBodyCellStyle(Workbook workbook) {
        return getBaseCellStyle(workbook);
    }

    private static CellStyle getBaseCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // font 设置字体
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        // align 设置对齐
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);

        // border 设置边界
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    public static <T> List<T> excelImport(InputStream inputStream, Class<T> cls) throws Exception {
        XSSFWorkbook book = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = book.getSheetAt(0);

        Field[] fields = cls.getDeclaredFields();

        List<T> dataList = new ArrayList<>();
        int cols;
        for (int i = POSITION_ROW; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i + 1); // 表头不算
            cols = POSITION_COL;
            AtomicInteger aj = new AtomicInteger(cols);
            T t = cls.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                handleField(t, getCellValue(row.getCell(aj.getAndIncrement())), field);
            }

            dataList.add(t);
        }

        book.close();
        return dataList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }
    }

    private static <T> void handleField(T t, String value, Field field) throws Exception {
        Class<?> type = field.getType();
        if (type == void.class || StringUtils.isBlank(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
            //数字类型
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, NumberUtils.toInt(value));
            } else if (type == long.class || type == Long.class) {
                field.set(t, NumberUtils.toLong(value));
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, NumberUtils.toByte(value));
            } else if (type == short.class || type == Short.class) {
                field.set(t, NumberUtils.toShort(value));
            } else if (type == double.class || type == Double.class) {
                field.set(t, NumberUtils.toDouble(value));
            } else if (type == float.class || type == Float.class) {
                field.set(t, NumberUtils.toFloat(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, CharUtils.toChar(value));
            } else if (type == boolean.class) {
                field.set(t, BooleanUtils.toBoolean(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t, BooleanUtils.toBoolean(value));
        } else if (type == Date.class) {
            //
            field.set(t, value);
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }

}
