package com.example.springBootTest.unit;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelUtil<T> {

	/**
	 * 日志组件
	 */
	protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * 读取excel
	 * 
	 * @param file
	 * @param t=对象类型
	 * @param fromType=对象标识
	 * @return 返回一个对象集合
	 * @throws IOException
	 */
	public List<T> readExcel(InputStream inputStream, String fileName, T t, String fromType) throws Exception {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(inputStream, t, fromType);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(inputStream, t, fromType);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	/**
	 * 
	 * 名称对应对象中的字段 excel 表的字段
	 * 
	 * @param fromType=对象标识
	 * @return
	 */
	public String dataSet(String fromType) {
		String dataSet = "";
		if (fromType.equals("accPmt")) {
			// 姓名 银行名称 银行卡号 提现金额
			dataSet = "realName,bankName,bankNo,pmtAmount";
		} else if (fromType.equals("user")) {
			dataSet = "cellphone,passward";
		}

		return dataSet;
	}

	/**
	 * 
	 * 读取office 2003 excel
	 * 
	 * @param file
	 * @param t=对象类型
	 * @param fromType=对象标识
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<T> read2003Excel(InputStream inputStream, T t, String fromType) throws Exception {
		List<T> list = new ArrayList<T>();
		HSSFWorkbook hwb = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			T tRow = (T) t.getClass().newInstance();
			String[] dataSet = this.dataSet(fromType).split(",");// 列
			for (int j = row.getFirstCellNum(); j <= dataSet.length - 1; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化number String字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字

				String setMethodName = "set" + dataSet[j].substring(0, 1).toUpperCase() + dataSet[j].substring(1);
				String getMethodName = "get" + dataSet[j].substring(0, 1).toUpperCase() + dataSet[j].substring(1);
				Class tCls = tRow.getClass();
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
				Method setMethod = tCls.getMethod(setMethodName, getMethod.getReturnType());

				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					setMethod.invoke(tRow, value);
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else if ("0.00_ ".equals(cell.getCellStyle().getDataFormatString())
							|| "0.00".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
						value = Float.parseFloat(value.toString());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						value = sdf.parse(value.toString());
					}
					setMethod.invoke(tRow, value);
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					setMethod.invoke(tRow, value);
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					setMethod.invoke(tRow, value);
					break;
				default:
					value = cell.toString();
					setMethod.invoke(tRow, value);
				}
				if (value == null || "".equals(value)) {
					continue;
				}
			}
			list.add(tRow);
		}
		return list;
	}

	/**
	 * 
	 * 读取Office 2007 excel
	 * 
	 * @param file
	 * @param t=对象类型
	 * @param fromType=对象标识
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<T> read2007Excel(InputStream inputStream, T t, String fromType) throws Exception {
		List<T> list = new ArrayList<T>();
		XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			T tRow = (T) t.getClass().newInstance();
			String[] dataSet = this.dataSet(fromType).split(",");// 列
			for (int j = row.getFirstCellNum(); j <= dataSet.length - 1; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化number String
				// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字

				String setMethodName = "set" + dataSet[j].substring(0, 1).toUpperCase() + dataSet[j].substring(1);
				String getMethodName = "get" + dataSet[j].substring(0, 1).toUpperCase() + dataSet[j].substring(1);

				Class tCls = tRow.getClass();

				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
				Method setMethod = tCls.getMethod(setMethodName, getMethod.getReturnType());

				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					setMethod.invoke(tRow, value);
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else if ("0.00_ ".equals(cell.getCellStyle().getDataFormatString())
							|| "0.00".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
						value = Float.parseFloat(value.toString());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						value = sdf.parse(value.toString());
					}
					setMethod.invoke(tRow, value);
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					setMethod.invoke(tRow, value);
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					setMethod.invoke(tRow, value);
					break;
				default:
					value = cell.toString();
					setMethod.invoke(tRow, value);
				}
				if (value == null || "".equals(value)) {
					continue;
				}
			}
			list.add(tRow);
		}
		return list;
	}
}
