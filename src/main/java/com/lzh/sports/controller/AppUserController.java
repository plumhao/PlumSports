package com.lzh.sports.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzh.sports.SysConst;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.*;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.dto.*;
import com.lzh.sports.tools.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 用户控制器
 */
@RestController()
@RequestMapping("/User")
public class AppUserController {
    @Autowired()
    private AppUserService _AppUserService;

    @Autowired()
    private AppUserMapper _AppUserMapper;


    /**
     * 用户分页查询接口
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<AppUserDto> List(@RequestBody AppUserPagedInput input, @RequestHeader("Authorization") String token) {

        return _AppUserService.List(input);
    }

    /**
     * 用户创建或则修改接口
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public AppUserDto CreateOrEdit(@RequestBody AppUserDto input) throws Exception {
        return _AppUserService.CreateOrEdit(input);
    }


    /**
     * 用户注册接口
     */
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public AppUserDto Register(@RequestBody AppUserDto input) throws Exception {

        return _AppUserService.Register(input);


    }

    /**
     * 找回密码
     */
    @RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
    public AppUserDto ForgetPassword(@RequestBody AppUserDto input) throws Exception {

        return _AppUserService.ForgetPassword(input);
    }


    /**
     * 用户删除接口
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input) {
        _AppUserService.removeById(input.getId());
    }

    /**
     * 用户批量删除接口
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input) {
        if (input.getIds().isEmpty()) {
            throw new CustomException("请选择需要删除的行");
        }
        _AppUserService.removeByIds(input.getIds());
    }

    /**
     * 单个用户查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public AppUserDto Get(@RequestBody AppUserPagedInput input) {

        return _AppUserService.Get(input);
    }

    /**
     * 用户导入
     */
    @RequestMapping(value = "/Import", method = RequestMethod.POST)
    public void Import(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file == null) {
            throw new CustomException("导入的文件不能为空");
        }

        //读取文件流
        InputStream is;
        is = file.getInputStream();
        //文件名
        String fileName = file.getOriginalFilename();
        if (!fileName.matches(SysConst.XLS_REGULAR) && !fileName.matches(SysConst.XLSX_REGULAR)) {
            throw new CustomException("导入的文件格式不对,只支持.xls,.xlsx");
        }
        Workbook wb = null;
        if (fileName.matches(SysConst.XLSX_REGULAR)) {
            //xlsx格式
            wb = new XSSFWorkbook(is);
        } else {
            //xls格式
            wb = new HSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);

        List<AppUser> items = new ArrayList<AppUser>();
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (i == sheet.getFirstRowNum()) {
                continue;
            }
            AppUser entity = new AppUser();
            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                String UserName = row.getCell(0).getStringCellValue();
                entity.setUserName(UserName);
            }
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                String Password = row.getCell(1).getStringCellValue();
                entity.setPassword(Password);
            }

            items.add(entity);

        }

        _AppUserService.saveBatch(items);


    }


    /**
     * 用户导出
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    public void Export(@RequestParam String query, HttpServletRequest request, HttpServletResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();


        AppUserPagedInput input = mapper.readValue(query, AppUserPagedInput.class);

        List<AppUserDto> items = _AppUserService.List(input).getItems();


        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("用户表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //表头数据
        String[] header = {"账户", "密码", "姓名", "邮箱", "手机号码", "地址", "用户角色", "出生年月"};
        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }


        for (int i = 0; i < items.size(); i++) {

            AppUserDto appUser = items.get(i);

            //创建一行
            HSSFRow row = sheet.createRow(i + 1);

            row.createCell(0).setCellValue(new HSSFRichTextString(appUser.getName()));

            row.createCell(1).setCellValue(new HSSFRichTextString(appUser.getPassword()));

            row.createCell(2).setCellValue(new HSSFRichTextString(appUser.getName()));


            row.createCell(4).setCellValue(new HSSFRichTextString(appUser.getPhoneNumber()));


            row.createCell(6).setCellValue(new HSSFRichTextString(appUser.RoleTypeFormat()));

            row.createCell(7).setCellValue(new HSSFRichTextString(Extension.LocalDateTimeConvertString(appUser.getBirth(), null)));

        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/SignIn", method = RequestMethod.POST)
    public ResponseData<String> SignIn(@RequestBody AppUserDto input, HttpServletRequest request) {
        String token = _AppUserService.SignIn(input);
        return ResponseData.GetResponseDataInstance(token, "登录成功", true);
    }

    /**
     * 获取用户信息
     */
    @SneakyThrows
    @RequestMapping(value = "/GetByToken", method = RequestMethod.POST)
    public AppUserDto GetByToken(@RequestHeader("Authorization") String token) {

        Integer userId = Extension.getTokenUserId(token);
        AppUserPagedInput querInput = new AppUserPagedInput();
        querInput.setId(userId);
        AppUserDto AppUserDto = _AppUserService.Get(querInput);
        //获取用户信息的时候把登录的时间写入到登录时间段
        String loginTimePeriod = AppUserDto.getLoginTimePeriod();
        String nowDate = Extension.GetNowDateFormat();
        if (Extension.isNotNullOrEmpty(loginTimePeriod)) {
            ArrayList<String> split = new ArrayList<String>(Arrays.stream(loginTimePeriod.split(",")).toList());

            if (split.stream().filter(x -> x.equals(nowDate)).count() == 0) {
                split.add(nowDate);
                AppUserDto.setLoginTimePeriod(String.join(",", split));
                _AppUserService.CreateOrEdit(AppUserDto);
            }
        } else {
            AppUserDto.setLoginTimePeriod(nowDate);
            _AppUserService.CreateOrEdit(AppUserDto);
        }
        return AppUserDto;
    }

    /**
     * 获取前30天的新用户数量
     */
    @SneakyThrows
    @RequestMapping(value = "/GetNewUserNumberByDay", method = RequestMethod.POST)
    public HashMap<String, Object> GetNewUserNumberByDay(@RequestHeader("Authorization") String token) {

        List<AppUser> appUsers = _AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery());

        Map<LocalDate, Long> collect = appUsers.stream().collect(Collectors.groupingBy(item -> item.getCreationTime().toLocalDate(), Collectors.counting()));

        ArrayList<String> label = new ArrayList<String>();
        ArrayList<Long> value = new ArrayList<Long>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        for (int i = 30; i > 0; i--) {

            LocalDate ld = LocalDate.now().plusDays(-i);
            label.add(ld.format(formatter));
            value.add(collect.getOrDefault(ld, Long.parseLong("0")));

        }
        HashMap<String, Object> rs = new HashMap<String, Object>();
        rs.put("Label", label);
        rs.put("Value", value);
        return rs;

    }


}
