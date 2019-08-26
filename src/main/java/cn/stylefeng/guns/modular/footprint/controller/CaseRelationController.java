package cn.stylefeng.guns.modular.footprint.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.footprint.service.ICaseInfoService;
import cn.stylefeng.guns.modular.footprint.service.ICaseRelationDetailService;
import cn.stylefeng.guns.modular.footprint.service.ICaseRelationService;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;
import cn.stylefeng.guns.modular.footprint.vo.CaseInfoPrintfootVO;
import cn.stylefeng.guns.modular.footprint.vo.CaseInfoVO;
import cn.stylefeng.guns.modular.footprint.vo.CaseRelationVO;
import cn.stylefeng.guns.modular.footprint.vo.CompareCaseInfoVO;
import cn.stylefeng.guns.modular.footprint.vo.FootprintVO;
import cn.stylefeng.guns.modular.system.dao.CaseInfoFootprintMapper;
import cn.stylefeng.guns.modular.system.model.CaseInfo;
import cn.stylefeng.guns.modular.system.model.CaseInfoPrintfoot;
import cn.stylefeng.guns.modular.system.model.CaseRelation;
import cn.stylefeng.guns.modular.system.model.CaseRelationDetail;
import cn.stylefeng.guns.modular.system.model.Footprint;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.FileUtil;

/**
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:29:55
 */
@Controller
@RequestMapping("/caseRelation")
public class CaseRelationController extends BaseController {

    private String PREFIX = "/footprint/caseRelation/";

    @Autowired
    private ICaseRelationService caseRelationService;
    @Autowired
    private ICaseInfoService caseInfoService;
    @Autowired
    private INoService noService ;
    @Autowired
    private IFootprintService footprintService;
    @Autowired
    private ICaseRelationDetailService caseRelationDetailService ;
    
    @Autowired
    private CaseInfoFootprintMapper caseInfoFootprintMapper ;
    /**
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "caseRelation.html";
    }
    @RequestMapping("loading/{caseNo}/{fpNo}")
    public String loading(@PathVariable(name="caseNo") String caseNo,@PathVariable(name="fpNo") String fpNo, Model model) {
    	model.addAttribute("caseNo",caseNo);
    	model.addAttribute("fpNo",fpNo);
        return PREFIX + "loading.html";
    }
    
    @RequestMapping("/mgr")
    public String indexMgr() {
        return PREFIX + "caseRelationMgr.html";
    }
    
    
    @RequestMapping("/caseRelation_input/{selectFpNo}/{fpNo}")
    public String caseRelation_input(@PathVariable(name="selectFpNo") String selectFpNo,@PathVariable(name="fpNo")  String fpNo, Model model) {
    	model.addAttribute("fpNo", fpNo);
    	model.addAttribute("selectFpNo", selectFpNo);
    	return PREFIX + "caseRelation_input.html";
    }
    
    @RequestMapping("/case_footprint_page")
    public String case_footprint_page() {
        return PREFIX + "case_footprint_query.html";
    }
    @RequestMapping("/case_footprint_page2")
    public String case_footprint_page2() {
        return PREFIX + "case_footprint_query2.html";
    }
    @RequestMapping("/autoCompareResult/{caseNo}/{fpNo}")
    public String autoCompareResult(@PathVariable(name="caseNo") String caseNo,@PathVariable(name="fpNo") String fpNo, Model model) {
    	CompareCaseInfoVO compareCaseInfoVO = new CompareCaseInfoVO();
    	CaseInfo caseInfo1 = caseInfoService.selectById(caseNo);
    	Footprint footprint1 = footprintService.selectById(fpNo);
    	FootprintVO footprintVo = CommonUtil.po2VO(footprint1, FootprintVO.class);
    	
    	footprintVo.setPositionName(ConstantFactory.me().getDictsByName("position", footprintVo.getPosition()));
    	footprintVo.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", footprintVo.getExtractionMethod()));
    	footprintVo.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", footprintVo.getLegacyMode()));
    	
    	CaseInfoVO caseInfoVo = CommonUtil.po2VO(caseInfo1, CaseInfoVO.class);
    	
    	caseInfoVo.setCaseStateName(ConstantFactory.me().getDictsByName("case_status", caseInfoVo.getCaseState()));
    	if(StringUtil.isNotEmpty(caseInfoVo.getUnit())) 
    		caseInfoVo.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(caseInfoVo.getUnit())));
    	caseInfoVo.setCaseTypeName(ConstantFactory.me().getDictsByName("case_type", caseInfoVo.getCaseType()));
    	caseInfoVo.setIntrusionModeName(ConstantFactory.me().getDictsByName("intrusion_mode", caseInfoVo.getIntrusionMode()));
    	
    	
    	footprintVo.setCaseInfo(caseInfoVo);
    	compareCaseInfoVO.setFootprint(footprintVo);
    	List<Footprint> list = footprintService.selectList(new EntityWrapper<Footprint>()
    			.isNotNull("case_no")
    			.eq("position", footprint1.getPosition())
    			.eq("Legacy_mode", footprint1.getLegacyMode())
    			.eq("extraction_method", footprint1.getExtractionMethod())
    			.notIn("case_no", caseNo));
    	if(CommonUtil.isEmpty(list)) {
    		model.addAttribute("autoCompareResult", compareCaseInfoVO);
    		return PREFIX + "autoCompareResult.html";
    	}
    	list = list.subList(0, list.size() > 10 ? 10 : list.size());
    	List<FootprintVO> listVo = new ArrayList<>();
    	for (Footprint footprint : list) {
    		FootprintVO f = CommonUtil.po2VO(footprint, FootprintVO.class);
    		
    		f.setPositionName(ConstantFactory.me().getDictsByName("position", f.getPosition()));
         	f.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", f.getExtractionMethod()));
         	f.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", f.getLegacyMode()));
    		
    		CaseInfo caseInfo2 = caseInfoService.selectById(f.getCaseNo());
    		CaseInfoVO caseInfoVO = CommonUtil.po2VO(caseInfo2, CaseInfoVO.class) ;
    		
    		
    		caseInfoVO.setCaseStateName(ConstantFactory.me().getDictsByName("case_status", caseInfoVO.getCaseState()));
        	if(StringUtil.isNotEmpty(caseInfoVO.getUnit())) 
        		caseInfoVO.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(caseInfoVO.getUnit())));
        	caseInfoVO.setCaseTypeName(ConstantFactory.me().getDictsByName("case_type", caseInfoVO.getCaseType()));
        	caseInfoVO.setIntrusionModeName(ConstantFactory.me().getDictsByName("intrusion_mode", caseInfoVO.getIntrusionMode()));
    		
        	f.setCaseInfo(caseInfoVO);
    		
    		listVo.add(f);
		}
    	compareCaseInfoVO.setFootprints(listVo);
    	model.addAttribute("autoCompareResult", compareCaseInfoVO);
    	return PREFIX + "autoCompareResult.html";
    }
    
    
    @RequestMapping("/autoCompare/{caseNo}/{fpNo}")
    @ResponseBody
    public Object autoCompare(@PathVariable(name="caseNo") String caseNo,@PathVariable(name="fpNo") String fpNo) {
    	CompareCaseInfoVO compareCaseInfoVO = new CompareCaseInfoVO();
    	CaseInfo caseInfo1 = caseInfoService.selectById(caseNo);
    	
    	Footprint footprint1 = footprintService.selectById(fpNo);
    	
    	List<Footprint> list = footprintService.selectList(new EntityWrapper<Footprint>()
    			.isNotNull("case_no")
    			.eq("position", footprint1.getPosition())
    			.eq("Legacy_mode", footprint1.getLegacyMode())
    			.eq("extraction_method", footprint1.getExtractionMethod())
    			.notIn("case_no", caseNo));
    	
    	if(CommonUtil.isEmpty(list)) {
    		return null ;
    	}
    	
    	try {
			Thread.sleep(1500);
//    		Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	int index = ((int)(list.size() * Math.random())) ;
    	
    	Footprint footprint2 = list.get(index) ;
    	CaseInfo caseInfo2 = caseInfoService.selectById(footprint2.getCaseNo());
    	
    	List<CaseInfoVO> caseInfos = new ArrayList<>();
    	caseInfos.add(CommonUtil.po2VO(caseInfo1, CaseInfoVO.class));
    	caseInfos.add(CommonUtil.po2VO(caseInfo2, CaseInfoVO.class));
    	compareCaseInfoVO.setCaseInfos(caseInfos);
    	
    	List<FootprintVO> footprints = new ArrayList<>();
    	footprints.add(CommonUtil.po2VO(footprint1, FootprintVO.class));
    	footprints.add(CommonUtil.po2VO(footprint2, FootprintVO.class));
    	compareCaseInfoVO.setFootprints(footprints);
    	
        return compareCaseInfoVO;
    }
    
    
    
    @RequestMapping("/relationCase/{fpNo}/{selectFpNo}")
    @ResponseBody
    public Object relationCase(@PathVariable(name="fpNo") String fpNo,@PathVariable(name="selectFpNo") String selectFpNo
    		,@RequestParam(name="relationName") String relationName
    		,@RequestParam(name="relationReason") String relationReason
    		,@RequestParam(name="remark",required=false) String remark) {
    	CaseRelation caseRelation = new CaseRelation();
    	caseRelation.setRelationNo(noService.busiNo("R"));
    	caseRelation.setRelationName(relationName);
    	caseRelation.setRelationReason(relationReason);
    	caseRelation.setRemark(remark);
    	EntityUtils.setCreateInfo(caseRelation);
    	
    	String[] fpNoArr = selectFpNo.split(",");
    	
    	Set<String> caseNos = new HashSet<>();
    	
    	Footprint footprint = footprintService.selectById(fpNo);
		footprint.setFpNo(noService.busiNo("F"));
    	EntityUtils.setCreateInfo(footprint);
    	footprint.setStatus("fp");
    	caseNos.add(footprint.getCaseNo());
    	footprint.setCaseNo(caseRelation.getRelationNo());
        footprintService.insert(footprint);
		
    	
    	
    	
    	for (String fpNoId : fpNoArr) {
    		footprint = footprintService.selectById(fpNoId);
    		footprint.setFpNo(noService.busiNo("F"));
        	EntityUtils.setCreateInfo(footprint);
        	footprint.setStatus("fp");
        	caseNos.add(footprint.getCaseNo());
        	footprint.setCaseNo(caseRelation.getRelationNo());
            footprintService.insert(footprint);
		}
    	
    	for (String caseNo : caseNos) {
    		CaseRelationDetail caseRelationDetail = new CaseRelationDetail();
			caseRelationDetail.setCaseNo(caseNo);
			caseRelationDetail.setId(CommonUtil.UUID());
			caseRelationDetail.setRelationNo(caseRelation.getRelationNo());
	    	caseRelationDetailService.insert(caseRelationDetail);
		}
        caseRelationService.insert(caseRelation);
    	return caseRelation.getRelationNo() ;
    }
    
    /**
     * @author whater
     * @param fpNo
     * @param position
     * @param legacyMode
     * @param extractionMethod
     * @return
     * @since JDK 1.8
     */
    @RequestMapping(value = "/case_footprint_query")
    @ResponseBody
    public Object case_footprint_query(
    		String caseNo,
    		String caseState,
    		String caseTmStart,
    		String caseTmEnd,
    		String caseAddress,
    		String unit,
    		String caseDesc,
    		String caseType,
    		String intrusionMode,
    		String stolenGoods,
    		String crimesPersonNum,
    		String fpNo,String position,String legacyMode,String extractionMethod) {
    	CaseInfoPrintfoot caseInfoPrintfoot = new CaseInfoPrintfoot();
    	if(StringUtil.isNotEmpty(caseNo))
    		caseInfoPrintfoot.setCaseNo(caseNo);
    	if(StringUtil.isNotEmpty(caseState))
    		caseInfoPrintfoot.setCaseState(caseState);
    	if(StringUtil.isNotEmpty(caseTmStart))
			try {
				caseInfoPrintfoot.setCaseTmStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(caseTmStart));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	if(StringUtil.isNotEmpty(caseTmEnd))
			try {
				caseInfoPrintfoot.setCaseTmEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(caseTmEnd));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	if(StringUtil.isNotEmpty(caseAddress))
    		caseInfoPrintfoot.setCaseAddress(caseAddress);
    	if(StringUtil.isNotEmpty(unit))
    		caseInfoPrintfoot.setUnit(unit);
    	if(StringUtil.isNotEmpty(caseDesc)) {
    		caseInfoPrintfoot.setCaseDesc(caseDesc.trim()+"%");
    	}
    	if(StringUtil.isNotEmpty(caseType))
    		caseInfoPrintfoot.setCaseType(caseType);
    	if(StringUtil.isNotEmpty(intrusionMode))
    		caseInfoPrintfoot.setIntrusionMode(intrusionMode);
    	if(StringUtil.isNotEmpty(stolenGoods))
    		caseInfoPrintfoot.setStolenGoods(stolenGoods);
    	if(StringUtil.isNotEmpty(crimesPersonNum))
    		caseInfoPrintfoot.setCrimesPersonNum(crimesPersonNum);
    	if(StringUtil.isNotEmpty(fpNo))
    		caseInfoPrintfoot.setFpNo(fpNo);
    	if(StringUtil.isNotEmpty(position))
    		caseInfoPrintfoot.setPosition(position);
    	if(StringUtil.isNotEmpty(legacyMode))
    		caseInfoPrintfoot.setLegacyMode(legacyMode);
    	if(StringUtil.isNotEmpty(extractionMethod))
    		caseInfoPrintfoot.setExtractionMethod(extractionMethod);
    	List<CaseInfoPrintfoot> list = caseInfoFootprintMapper.selectAll(caseInfoPrintfoot);
    	
    	List<CaseInfoPrintfootVO> listVo = CommonUtil.listPo2VO(list, CaseInfoPrintfootVO.class);
    	listVo.forEach((vo)->{
    		
    		vo.setCaseStateName(ConstantFactory.me().getDictsByName("case_status", vo.getCaseState()));
        	if(StringUtil.isNotEmpty(vo.getUnit())) 
        		vo.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getUnit())));
        	vo.setCaseTypeName(ConstantFactory.me().getDictsByName("case_type", vo.getCaseType()));
        	vo.setIntrusionModeName(ConstantFactory.me().getDictsByName("intrusion_mode", vo.getIntrusionMode()));
        	vo.setPositionName(ConstantFactory.me().getDictsByName("position", vo.getPosition()));
    		
    		
    		vo.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", vo.getExtractionMethod()));
         	vo.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", vo.getLegacyMode()));
         	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
     			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
     		}
     		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
     			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
     		}
     		/*String img = vo.getOriginalImg() ;
     		File file = new File("D:/tmp/"+img);
     		try {
     			File newFile = new File("D:/temp/"+img) ;
     			newFile.createNewFile();
				cn.stylefeng.guns.modular.footprint.util.FileUtil.copy(file, newFile);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
    	});
    	
    	return listVo ;
    }
    
    
    @RequestMapping(value = "UserExcelDownloads")
    public void downloadAllClassmate(String caseNo,
    		@RequestParam(name="caseState") String caseState,
    		@RequestParam(name="caseTmStart") String caseTmStart,
    		@RequestParam(name="caseTmEnd") String caseTmEnd,
    		@RequestParam(name="caseAddress") String caseAddress,
    		@RequestParam(name="unit") String unit,
    		@RequestParam(name="caseDesc") String caseDesc,
    		@RequestParam(name="caseType") String caseType,
    		@RequestParam(name="intrusionMode") String intrusionMode,
    		@RequestParam(name="stolenGoods") String stolenGoods,
    		@RequestParam(name="crimesPersonNum") String crimesPersonNum,
    		@RequestParam(name="fpNo") String fpNo,
    		@RequestParam(name="position") String position,
    		@RequestParam(name="legacyMode") String legacyMode,
    		@RequestParam(name="extractionMethod") String extractionMethod,
    		HttpServletResponse response) throws IOException {
    	
    	
    	List<CaseInfoPrintfootVO> listVo = (List<CaseInfoPrintfootVO>) 
    			this.case_footprint_query(
    					caseNo, 
    					caseState, 
    					caseTmStart, 
    					caseTmEnd, 
    					caseAddress, 
    					unit, 
    					caseDesc, 
    					caseType, 
    					intrusionMode, 
    					stolenGoods, 
    					crimesPersonNum, 
    					fpNo, position, legacyMode, extractionMethod);
    	
    	
    	
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据");
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(CommonUtil.getSystemDate()) +"_"+ CommonUtil.UUID()  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { 
        		"足迹编号", 
        		"足迹遗留部位", 
        		"足迹遗留方式", 
        		"足迹提取方式", 
        		"案件编号", 
        		"案件状态", 
        		"案发时间", 
        		"案发地点", 
        		"所属单位", 
        		"简要案情", 
        		"案件类别", 
        		"侵入方式", 
        		"被盗物品", 
        		"作案人数", 
        		"创建人", 
        		"创建机构", 
        		"创建时间"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        //在表中存放查询到的数据放入对应的列
        for (CaseInfoPrintfootVO vo : listVo) {
            HSSFRow row1 = sheet.createRow(rowNum);
            int index = 0 ;
            row1.createCell(index++).setCellValue(vo.getFpNo());
            row1.createCell(index++).setCellValue(vo.getPositionName());
            row1.createCell(index++).setCellValue(vo.getLegacyModeName());
            row1.createCell(index++).setCellValue(vo.getExtractionMethodName());
            
            row1.createCell(index++).setCellValue(vo.getCaseNo());
            row1.createCell(index++).setCellValue(vo.getCaseStateName());
            if(vo.getCaseTm() == null) {
            	row1.createCell(index++).setCellValue("");
            }else {
            	row1.createCell(index++).setCellValue(simpleDateFormat.format(vo.getCaseTm()));
            }
            
            row1.createCell(index++).setCellValue(vo.getCaseAddress());
            row1.createCell(index++).setCellValue(vo.getUnitName());
            row1.createCell(index++).setCellValue(vo.getCaseDesc());
            row1.createCell(index++).setCellValue(vo.getCaseTypeName());
            row1.createCell(index++).setCellValue(vo.getIntrusionModeName());
            row1.createCell(index++).setCellValue(vo.getStolenGoods());
            row1.createCell(index++).setCellValue(vo.getCrimesPersonNum());
            row1.createCell(index++).setCellValue(vo.getCreateUserName());
            row1.createCell(index++).setCellValue(vo.getCreateOrgName());
            
            if(vo.getCrtTm() == null) {
            	row1.createCell(index++).setCellValue("");
            }else {
            	row1.createCell(index++).setCellValue(simpleDateFormat.format(vo.getCrtTm()));
            }
            
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
    
    
    /**
     * @return
     */
    @RequestMapping("/imageCompareImages/{images}")
    public String imageCompareImages(@PathVariable String images, Model model) {
    	model.addAttribute("images",images);
    	return PREFIX + "imageCompareImages.html";
    }
    
    /**
     * @return
     */
    @RequestMapping("/imageCompare/{caseRelationId}")
    public String ImageCompare(@PathVariable String caseRelationId, Model model) {
    	List<CaseRelationDetail> list = caseRelationDetailService.selectList(new EntityWrapper<CaseRelationDetail>().eq("relation_no", caseRelationId));
    	List<CaseInfo> listCaseInfo = new ArrayList<>();
    	list.forEach((e)->listCaseInfo.add(caseInfoService.selectById(e.getCaseNo())));
    	Set<String> images = new HashSet<>();
    	Set<Footprint> footprints = new HashSet<>();
    	for (CaseInfo caseInfo : listCaseInfo) {
    		List<Footprint> listFootprint = footprintService.selectList(new EntityWrapper<Footprint>().eq("case_no", caseInfo.getCaseNo()));
            for (Footprint footprint : listFootprint) {
            	images.add(footprint.getOriginalImg());
            	footprints.add(footprint);
     		}
		}
    	String imgs = "" ;
    	for (String image : images) {
    		imgs += image + ",";
		}
    	model.addAttribute("footprints",footprints);
    	model.addAttribute("images",imgs);
    	LogObjectHolder.me().set(caseRelationId);
        return PREFIX + "imageCompare.html";
    }
    
    /**
     * @return
     */
    @RequestMapping("/imageCompare_v2/{caseRelationId}")
    public String ImageCompare_v2(@PathVariable String caseRelationId, Model model) {
    	List<CaseRelationDetail> list = caseRelationDetailService.selectList(new EntityWrapper<CaseRelationDetail>().eq("relation_no", caseRelationId));
    	List<CaseInfo> listCaseInfo = new ArrayList<>();
    	list.forEach((e)->listCaseInfo.add(caseInfoService.selectById(e.getCaseNo())));
    	List<CaseInfoVO> listCaseInfoVO = CommonUtil.listPo2VO(listCaseInfo, CaseInfoVO.class);
    	for (CaseInfoVO caseInfo : listCaseInfoVO) {
    		List<Footprint> listFootprint = footprintService.selectList(new EntityWrapper<Footprint>().eq("case_no", caseInfo.getCaseNo()));
    		List<FootprintVO> listFootprintVO = CommonUtil.listPo2VO(listFootprint, FootprintVO.class);
    		caseInfo.setImageInfos(listFootprintVO);
		}
    	model.addAttribute("caseInfos",listCaseInfoVO);
    	LogObjectHolder.me().set(caseRelationId);
        return PREFIX + "imageCompare_v2.html";
    }
    /**
     */
    @RequestMapping("/caseRelation_add")
    public String caseRelationAdd() {
        return PREFIX + "caseRelation_add.html";
    }

    /**
     */
    @RequestMapping("/caseRelation_update/{caseRelationId}")
    public String caseRelationUpdate(@PathVariable String caseRelationId, Model model) {
        CaseRelation caseRelation = caseRelationService.selectById(caseRelationId);
        model.addAttribute("item",caseRelation);
        LogObjectHolder.me().set(caseRelation);
        return PREFIX + "caseRelation_edit.html";
    }
    /**
     */
    @RequestMapping("/caseRelationMgr_update/{caseRelationId}")
    public String caseRelationMgrUpdate(@PathVariable String caseRelationId, Model model) {
        CaseRelation caseRelation = caseRelationService.selectById(caseRelationId);
        Wrapper<CaseRelationDetail> entityWrapper = new EntityWrapper<CaseRelationDetail>() ;
        entityWrapper.eq("relation_no", caseRelation.getRelationNo());
        List<CaseRelationDetail> listCaseRelationDetail = caseRelationDetailService.selectList(entityWrapper);
        List<String> ids = new ArrayList<>();
        listCaseRelationDetail.forEach((e)->ids.add(e.getCaseNo()));
        List<CaseInfoVO> listVo =  new ArrayList<>();
        if(CommonUtil.isNotEmpty(ids)) {
        	List<CaseInfo> list = caseInfoService.selectList(new EntityWrapper<CaseInfo>().in("case_no", ids));
            listVo = CommonUtil.listPo2VO(list, CaseInfoVO.class);
            listVo.forEach((vo)->{
            	vo.setCaseStateName(ConstantFactory.me().getDictsByName("case_status", vo.getCaseState()));
            	if(StringUtil.isNotEmpty(vo.getUnit())) 
            		vo.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getUnit())));
            	vo.setCaseTypeName(ConstantFactory.me().getDictsByName("case_type", vo.getCaseType()));
            	vo.setIntrusionModeName(ConstantFactory.me().getDictsByName("intrusion_mode", vo.getIntrusionMode()));
            	
            	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
        			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
        		}
        		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
        			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
        		}
            });
        }
       
        model.addAttribute("caseInfos",listVo);
        model.addAttribute("item",caseRelation);
        LogObjectHolder.me().set(caseRelation);
        return PREFIX + "caseRelationMgr_edit.html";
    }
    
    /**
     */
    @RequestMapping("/caseRelationMgr_detail/{caseRelationId}")
    public String caseRelationMgr_detail(@PathVariable String caseRelationId, Model model) {
        CaseRelation caseRelation = caseRelationService.selectById(caseRelationId);
        Wrapper<CaseRelationDetail> entityWrapper = new EntityWrapper<CaseRelationDetail>() ;
        entityWrapper.eq("relation_no", caseRelation.getRelationNo());
        List<CaseRelationDetail> listCaseRelationDetail = caseRelationDetailService.selectList(entityWrapper);
        List<String> ids = new ArrayList<>();
        listCaseRelationDetail.forEach((e)->ids.add(e.getCaseNo()));
        List<CaseInfoVO> listVo =  new ArrayList<>();
        if(CommonUtil.isNotEmpty(ids)) {
        	List<CaseInfo> list = caseInfoService.selectList(new EntityWrapper<CaseInfo>().in("case_no", ids));
            listVo = CommonUtil.listPo2VO(list, CaseInfoVO.class);
            listVo.forEach((vo)->{
            	vo.setCaseStateName(ConstantFactory.me().getDictsByName("case_status", vo.getCaseState()));
            	if(StringUtil.isNotEmpty(vo.getUnit())) 
            		vo.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getUnit())));
            	vo.setCaseTypeName(ConstantFactory.me().getDictsByName("case_type", vo.getCaseType()));
            	vo.setIntrusionModeName(ConstantFactory.me().getDictsByName("intrusion_mode", vo.getIntrusionMode()));
            	
            	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
        			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
        		}
        		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
        			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
        		}
            });
        }
        
        
        List<Footprint> listFootprint = footprintService.selectList(new EntityWrapper<Footprint>().eq("case_no", caseRelationId));
        List<FootprintVO> listFootprintVo = CommonUtil.listPo2VO(listFootprint,FootprintVO.class);
        for (FootprintVO footprintVO : listFootprintVo) {
        	footprintVO.setPositionName(ConstantFactory.me().getDictsByName("position", footprintVO.getPosition()));
        	footprintVO.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", footprintVO.getExtractionMethod()));
        	footprintVO.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", footprintVO.getLegacyMode()));
		}
        
        model.addAttribute("footprints",listFootprintVo);
        model.addAttribute("caseInfos",listVo);
        model.addAttribute("item",caseRelation);
        LogObjectHolder.me().set(caseRelation);
        return PREFIX + "caseRelationMgr_detail.html";
    }
    
    
    /**
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<CaseRelationVO> listVo = CommonUtil.listPo2VO(caseRelationService.selectList(new EntityWrapper<CaseRelation>().orderBy("crt_tm desc")), CaseRelationVO.class);
    	listVo.forEach((vo)->{
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
     * 新增串并案件
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CaseRelation caseRelation) {
    	caseRelation.setRelationNo(noService.busiNo("R"));
    	EntityUtils.setCreateInfo(caseRelation);
        caseRelationService.insert(caseRelation);
        return SUCCESS_TIP;
    }

    
    /**
     * 新增串并案件
     */
    @RequestMapping(value = "/addRel")
    @ResponseBody
    public Object addRel(@RequestBody CaseRelationVO caseRelation) {
    	caseRelationDetailService.delete(new EntityWrapper<CaseRelationDetail>().eq("relation_no", caseRelation.getRelationNo()));
    	if(CommonUtil.isNotEmpty(caseRelation.getCaseInfos())) {
    		caseRelation.getCaseInfos().forEach((caseInfos)->{
    			CaseRelationDetail caseRelationDetail = new CaseRelationDetail();
    			caseRelationDetail.setCaseNo(caseInfos.getCaseNo());
    			caseRelationDetail.setId(CommonUtil.UUID());
    			caseRelationDetail.setRelationNo(caseRelation.getRelationNo());
    	    	caseRelationDetailService.insert(caseRelationDetail);
    		});
    	}
        return SUCCESS_TIP;
    }
    
    /**
     * 删除串并案件
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String caseRelationId) {
        caseRelationService.deleteById(caseRelationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改串并案件
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CaseRelation caseRelation) {
    	EntityUtils.setUpdateInfo(caseRelation);
        caseRelationService.updateById(caseRelation);
        return SUCCESS_TIP;
    }

    /**
     * 串并案件详情
     */
    @RequestMapping(value = "/detail/{caseRelationId}")
    @ResponseBody
    public Object detail(@PathVariable("caseRelationId") String caseRelationId) {
        return caseRelationService.selectById(caseRelationId);
    }
}
