package com.qibao.backend.api.finance;

import com.qibao.backend.feign.IGoldBalanceFeign;
import com.qibao.backend.utils.ExportExcel;
import com.qibao.common.dto.BaseResponse;
import com.qibao.finance.vo.GoldBalanceVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by ljn on 2018/2/6.
 */
@RestController
@RequestMapping("backend/gold")
public class GoldBalanceController extends BaseResponse{

    @Autowired
    private IGoldBalanceFeign goldBalanceFeign;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final String FILES_EXPORT_PATH = "/srv/qibao/goldBalance/";

    @RequestMapping(value = "queryGoldBalanceList",method = RequestMethod.GET)
    public BaseResponse<GoldBalanceVO> queryGoldBalanceList(@RequestParam(value="startTime",required = false)String startTime,
                                                            @RequestParam(value="endTime",required = false)String endTime,
                                                            @RequestParam(value="page",required = false)Integer page,
                                                            @RequestParam(value="size",required = false)Integer size) throws ParseException {
        return goldBalanceFeign.selectGoldBalance(startTime, endTime, page, size);
    }

    @RequestMapping(value="exportGoldBalance",method = RequestMethod.GET)
    public BaseResponse exportGoldBalance(@RequestParam(value="startTime",required = false)String startTime,
                                          @RequestParam(value="endTime",required = false)String endTime,
                                          HttpServletResponse response) throws ParseException, IOException {
        BaseResponse<GoldBalanceVO> goldResponse = new BaseResponse<>();

        List<GoldBalanceVO> list = goldBalanceFeign.selectGoldBalance(startTime, endTime,0,10000).getData();

        String path = exportGold(list);

        download(response, path);

        return goldResponse;
    }

    public String exportGold(List<GoldBalanceVO> list) {

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet();

        ExportExcel exportExcel = new ExportExcel(wb, sheet);

        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 指定单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        String headString = "金币平衡表导出";
        int columnSize = 20;
        exportExcel.createNormalHead(0, headString, columnSize - 1);

        String[] columnHeader = new String[]{
                "开始时间",
                "期初金币",
                "平台赠送金币",
                "购买金币",
                "存入金币",
                "取回金币",
                "结束时间",
                "Roll房送出金币",
                "Roll房获得金币",
                "开箱消耗金币",
                "开箱获得金币",
                "开箱奖金池金币",
                "开箱奖金库金币",
                "取回手续费",
                "差异",
                "期末金币",
                "开箱奖金池金币累计",
                "开箱奖金库金币累计",
                "平台获取金币",
                "平台获取金币累计",
        };
        exportExcel.createColumHeader(1, columnHeader);

        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);

        //设置列的宽度
        sheet.setColumnWidth(0, 9 * 10 * 60);
        sheet.setColumnWidth(1, 9 * 10 * 60);
        sheet.setColumnWidth(2, 9 * 10 * 60);
        sheet.setColumnWidth(3, 9 * 10 * 60);
        sheet.setColumnWidth(4, 9 * 10 * 60);
        sheet.setColumnWidth(5, 9 * 10 * 60);
        sheet.setColumnWidth(6, 9 * 10 * 60);
        sheet.setColumnWidth(7, 9 * 10 * 60);
        sheet.setColumnWidth(8, 9 * 10 * 60);
        sheet.setColumnWidth(9, 9 * 10 * 60);
        sheet.setColumnWidth(10, 9 * 10 * 60);
        sheet.setColumnWidth(11, 9 * 10 * 60);
        sheet.setColumnWidth(12, 9 * 10 * 60);
        sheet.setColumnWidth(13, 9 * 10 * 60);
        sheet.setColumnWidth(14, 9 * 10 * 60);
        sheet.setColumnWidth(15, 9 * 10 * 60);
        sheet.setColumnWidth(16, 9 * 10 * 60);
        sheet.setColumnWidth(17, 9 * 10 * 60);
        sheet.setColumnWidth(18, 9 * 10 * 60);
        sheet.setColumnWidth(19, 9 * 10 * 60);

        // 循环创建中间的单元格的各项的值
        if (!CollectionUtils.isEmpty(list)) {
            int i = 2;
            for (GoldBalanceVO item : list) {
                HSSFRow row = sheet.createRow((short) i++);

                /* 开始时间 */
                String beginTime = item.getBeginTime() == null ? "" : format.format(item.getBeginTime());
                /* 期初金币 */
                String beginGold = item.getBeginGold() == null ? "0.0" : String.valueOf(item.getBeginGold());
                /* 平台赠送金币 */
                String platformGiveGold = item.getPlatformGiveGold() == null ? "0.0" : String.valueOf(item.getPlatformGiveGold());
                /* 购买金币 */
                String buyGold = item.getBuyGold() == null ? "0.0" : String.valueOf(item.getBuyGold());
                /* 存入金币 */
                String depositGold = item.getDepositGold() == null ? "0.0" : String.valueOf(item.getDepositGold());
                /* 取回金币 */
                String getBackGold = item.getGetBackGold() == null ? "0.0" : String.valueOf(item.getGetBackGold());
                /* 结束时间 */
                String endTime = item.getEndTime() == null ? "" : format.format(item.getEndTime());
                /* Roll房送出金币 */
                String rollGiveGold = item.getRollGiveGold() == null ? "0.0" : String.valueOf(item.getRollGiveGold());
                /* Roll房获得金币 */
                String rollGetGold = item.getRollGetGold() == null ? "0.0" : String.valueOf(item.getRollGetGold());
                /* 开箱消耗金币 */
                String openBoxConsumeGold = item.getOpenBoxConsumeGold() == null ? "0.0" : String.valueOf(item.getOpenBoxConsumeGold());
                /* 开箱获得金币 */
                String openBoxGetGold = item.getOpenBoxGetGold() == null ? "0.0" : String.valueOf(item.getOpenBoxGetGold());
                /* 开箱奖金池金币 */
                String goldPool = item.getGoldPool() == null ? "0.0" : String.valueOf(item.getGoldPool());
                /* 开箱奖金库金币 */
                String goldRepository = item.getGoldRepository() == null ? "0.0" : String.valueOf(item.getGoldRepository());
                /* 取回手续费 */
                String poundage = item.getPoundage() == null ? "0.0" : String.valueOf(item.getPoundage());
                /* 差异 */
                String diffGold = item.getDiffGold() == null ? "0.0" : String.valueOf(item.getDiffGold());
                /* 期末金币 */
                String endGold = item.getEndGold() == null ? "0.0" : String.valueOf(item.getEndGold());
                /* 开箱奖金池金币累计 */
                String totalGoldPool = item.getTotalGoldPool() == null ? "0.0" : String.valueOf(item.getTotalGoldPool());
                /* 开箱奖金库金币累计 */
                String totalGoldRepository = item.getTotalGoldRepository() == null ? "0.0" : String.valueOf(item.getTotalGoldRepository());
                /* 平台获取金币 */
                String platformGetGold = item.getPlatformGetGold() == null ? "0.0" : String.valueOf(item.getPlatformGetGold());
                /* 平台获取金币累计 */
                String totalPlatformGetGold = item.getTotalPlatformGetGold() == null ? "0.0" : String.valueOf(item.getTotalPlatformGetGold());
                exportExcel.createCell(wb, row, 0, CellStyle.ALIGN_CENTER, beginTime);
                exportExcel.createCell(wb, row, 1, CellStyle.ALIGN_CENTER, beginGold);
                exportExcel.createCell(wb, row, 2, CellStyle.ALIGN_CENTER, platformGiveGold);
                exportExcel.createCell(wb, row, 3, CellStyle.ALIGN_CENTER, buyGold);
                exportExcel.createCell(wb, row, 4, CellStyle.ALIGN_CENTER, depositGold);
                exportExcel.createCell(wb, row, 5, CellStyle.ALIGN_CENTER, getBackGold);
                exportExcel.createCell(wb, row, 6, CellStyle.ALIGN_CENTER, endTime);
                exportExcel.createCell(wb, row, 7, CellStyle.ALIGN_CENTER, rollGiveGold);
                exportExcel.createCell(wb, row, 8, CellStyle.ALIGN_CENTER, rollGetGold);
                exportExcel.createCell(wb, row, 9, CellStyle.ALIGN_CENTER, openBoxConsumeGold);
                exportExcel.createCell(wb, row, 10, CellStyle.ALIGN_CENTER, openBoxGetGold);
                exportExcel.createCell(wb, row, 11, CellStyle.ALIGN_CENTER, goldPool);
                exportExcel.createCell(wb, row, 12, CellStyle.ALIGN_CENTER, goldRepository);
                exportExcel.createCell(wb, row, 13, CellStyle.ALIGN_CENTER, poundage);
                exportExcel.createCell(wb, row, 14, CellStyle.ALIGN_CENTER, diffGold);
                exportExcel.createCell(wb, row, 15, CellStyle.ALIGN_CENTER, endGold);
                exportExcel.createCell(wb, row, 16, CellStyle.ALIGN_CENTER, totalGoldPool);
                exportExcel.createCell(wb, row, 17, CellStyle.ALIGN_CENTER, totalGoldRepository);
                exportExcel.createCell(wb, row, 18, CellStyle.ALIGN_CENTER, platformGetGold);
                exportExcel.createCell(wb, row, 19, CellStyle.ALIGN_CENTER, totalPlatformGetGold);
            }
        }

        File file = new File(FILES_EXPORT_PATH);
        file.mkdirs();
        String filePath = FILES_EXPORT_PATH + "/" + UUID.randomUUID().toString() + ".xls";
        exportExcel.outputExcel(filePath);
        return filePath;
    }

    public void download(HttpServletResponse response, String path) throws IOException {
        //读到流中
        InputStream in = new FileInputStream(path);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("application/msexcel");
        response.addHeader("Content-Disposition", "attachment; filename=\"goldBalance.xls\"");
        OutputStream out = response.getOutputStream();
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
            }
        } finally {
            out.flush();
            in.close();
            out.close();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
