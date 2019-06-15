package cn.stylefeng.guns.modular.footprint.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.core.util.StringUtil;

import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Footprint;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;
import cn.stylefeng.guns.modular.footprint.vo.FootprintVO;

/**
 * 足迹信息控制器
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:30:42
 */
@Controller
@RequestMapping("/footprint")
public class FootprintController extends BaseController {

    private String PREFIX = "/footprint/footprint/";

    @Autowired
    private IFootprintService footprintService;
    @Autowired
    private INoService noService ;
    /**
     * 跳转到足迹信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "footprint.html";
    }
    /**
     * 跳转到足迹信息首页
     */
    @RequestMapping("query")
    public String index_query() {
        return PREFIX + "footprint_query.html";
    }

    /**
     * 跳转到添加足迹信息
     */
    @RequestMapping("/footprint_add")
    public String footprintAdd() {
        return PREFIX + "footprint_add.html";
    }

    /**
     * 跳转到修改足迹信息
     */
    @RequestMapping("/footprint_update/{footprintId}")
    public String footprintUpdate(@PathVariable String footprintId, Model model) {
        Footprint footprint = footprintService.selectById(footprintId);
        model.addAttribute("item",footprint);
        LogObjectHolder.me().set(footprint);
        return PREFIX + "footprint_edit.html";
    }

    /**
     * 获取足迹信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String fpNo,String position,String legacyMode,String extractionMethod) {
    	Wrapper<Footprint> entityWrapper = new EntityWrapper<Footprint>().orderBy("crt_tm desc") ;
    	if(StringUtil.isNotEmpty(fpNo))
    		entityWrapper.eq("fp_no", fpNo);
    	if(StringUtil.isNotEmpty(position))
    		entityWrapper.like("position", position);
    	if(StringUtil.isNotEmpty(legacyMode))
    		entityWrapper.eq("legacy_mode", legacyMode);
    	if(StringUtil.isNotEmpty(extractionMethod))
    		entityWrapper.eq("extraction_method", extractionMethod);
    	entityWrapper.eq("status", "fp");
    	List<FootprintVO> listVo = CommonUtil.listPo2VO(footprintService.selectList(entityWrapper), FootprintVO.class);
    	listVo.forEach((vo)->{
         	vo.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", vo.getExtractionMethod()));
         	vo.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", vo.getLegacyMode()));
         	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
     			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
     		}
     		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
     			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
     		}
        });
        return listVo;
    }

    /**
     * 新增足迹信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Footprint footprint) {
    	footprint.setFpNo(noService.busiNo("F"));
    	EntityUtils.setCreateInfo(footprint);
    	footprint.setStatus("fp");
        footprintService.insert(footprint);
        return SUCCESS_TIP;
    }

    /**
     * 删除足迹信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String footprintId) {
        footprintService.deleteById(footprintId);
        return SUCCESS_TIP;
    }

    /**
     * 修改足迹信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody Footprint footprint) {
    	EntityUtils.setUpdateInfo(footprint);
        footprintService.updateById(footprint);
        return SUCCESS_TIP;
    }

    /**
     * 足迹信息详情
     */
    @RequestMapping(value = "/detail/{footprintId}")
    @ResponseBody
    public Object detail(@PathVariable("footprintId") String footprintId) {
        return footprintService.selectById(footprintId);
    }
    
    
    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据");
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(CommonUtil.getSystemDate()) +"_"+ CommonUtil.UUID()  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "足迹编号", "足迹遗留部位", "足迹遗留方式", "足迹提取方式", "地址"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        List<FootprintVO> listVo = CommonUtil.listPo2VO(footprintService.selectList(new EntityWrapper<Footprint>().eq("status", "fp")), FootprintVO.class);
    	listVo.forEach((vo)->{
         	vo.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", vo.getExtractionMethod()));
         	vo.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", vo.getLegacyMode()));
         	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
     			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
     		}
     		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
     			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
     		}
        });
        //在表中存放查询到的数据放入对应的列
        for (FootprintVO vo : listVo) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(vo.getFpNo());
            row1.createCell(1).setCellValue(vo.getPosition());
            row1.createCell(2).setCellValue(vo.getLegacyModeName());
            row1.createCell(3).setCellValue(vo.getExtractionMethodName());
            row1.createCell(4).setCellValue("http://1b6f311445.imwork.net:18184/kaptcha/"+vo.getOriginalImg());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
