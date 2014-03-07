package org.hmahout.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Constants {
	
	/**
	 * 网机开通状态
	 */
	public final static Integer PHONE_STATUS_VALID_REGIST = 0;//注册
	public final static Integer PHONE_STATUS_VALID_ACTIVE = 1;//开通
	public final static Integer PHONE_STATUS_VALID_TRANSFER = 2;//转发
	public final static Integer PHONE_STATUS_VALID_CANCLE = 3;//注销
	public final static String PHONE_STATUS_VALID = "phone_status_valid";
	
	// 可添加的子网机上限，默认为5个
	public final static Integer ATTR_TOP_LIMLIT_NUM = 5;
	
	//网机验证状态
	public final static Integer PHONE_VERIFY_STATUS_NOVERIFY = 0;//未验证
	public final static Integer PHONE_VERIFY_STATUS_PERSON = 1;//个人身份认证
	public final static Integer PHONE_VERIFY_STATUS_COMPANY = 2;//企业法人认证
	public final static Integer PHONE_VERIFY_STATUS_PER_COM = 3;//个人+法人
	public final static String PHONE_VERIFY_STATUS = "phone_verufy_status";
	
	//网机会话接听方式
	public final static Integer PHONE_SESSION_AUTO_ALL_AUTO = 0;//全自动接听
	public final static Integer PHONE_SESSION_AUTO_ASSIGN = 1;//指定自动接听名单
	public final static Integer PHONE_SESSION_AUTO_MANUAL = 2;//手工接听
	
	//网机的根结构编号
	public final static String PHONE_GRADE_MAIN = "01";	
	public final static String PHONE_ADMIN_ID = "10001";
	
	public final static String ACTIVE_RESULT= "active_result";
	public final static int ACTIVE_RESULT_OK= 0;
	public final static int ACTIVE_RESULT_FAIL= 1;
	//子网机分隔符
	public final static String SUB_PHONE_SEP = "#";
	// 网机统计列表
	public final static String PHONE_STATUS_LIST = "phone_status_list";
	// 网机统计和
	public final static String PHONE_STATUS_SUM = "phone_status_sum";
	
	// 对外显示撮合
	public final static String TRADE_MATCH_WEB_LIST = "trade_match_web_list";
	public final static String TRADE_MATCH_WEB = "trade_match_web";
	
	//网机交易记录
	public final static String TRADE_DEAL_LIST = "trade_deal_list";
	public final static String TRADE_DEAL = "trade_deal";
	// 网机撮合成交记录
	public final static String TRADE_MATCH_LIST = "trade_match_list";
	public final static String TRADE_MATCH = "trade_match";
	// 网机撮合成交交易记录
	public final static String TRADE_CHAT_RECORD_LIST = "trade_chat_record_list";
	public final static String TRADE_CHAT_RECORD = "trade_chat_record";
	
	/**
	 * 验证码有效期，默认2天
	 */
	public final static int PHONE_ACTIVE_EXPIRYDAYS = 2;
	
	/**
	 * 网站关键字，用于更新为空的参数值
	 */
	public final static String WEB_KEY_NULL = "__N_U_L_L__";
	public final static String WEB_ADMIN_NUM = "01";//网站管理员岗位号
	
	/**
	 * 分页
	 */
	public final static String ATTR_PAGE = "_attr_page";
	public final static String ATTR_TOTAL_PAGE = "_attr_total_page";
	public final static String ATTR_TOTAL_RECODE = "_attr_total_recode";
	
	/**
	 * 常用sql
	 */
	public static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
	public static final String SQL_FOUND_ROWS = "SELECT FOUND_ROWS()";
	public static final String SQL_KEY_ROWCALE ="SQL_CALC_FOUND_ROWS";
	
	/**
	 * cookie管理
	 */
	public final static String COOKIE_LOGIN_USERNAME = "cookie_login_user";
	public final static String COOKIE_LOGIN_PASSWORD = "cookie_login_password";
	public final static String COOKIE_JOB_USERNAME = "cookie_job_user";
	public final static String COOKIE_JOB_PASSWORD = "cookie_job_password";	
	public final static String COOKIE_PATH = "/";
	
	/**
	 * 数据库表的状态值
	 */
	public final static Integer RECORD_STATUS_NEGATIVE = -1;//
	public final static Integer RECORD_STATUS_OFF = 0;//没有开通
	public final static Integer RECORD_STATUS_ON = 1;//开通
	public final static Integer RECORD_STATUS_STALE = 2;//过期
	public final static Integer RECORD_STATUS_VERIFY = 3;//没有验证
	
	/**
	 * 网站在session或者request中保存的属性键值
	 * 用户相关
	 */
	public final static String ATTR_AUTHBEAN = "attr_authbean";
	public final static String ATTR_PHONE = "attr_phone";
	public final static String ATTR_PHONE_PERSON = "attr_phone_person";
	public final static String ATTR_PHONE_COMPANY = "attr_phone_company";
	public final static String ATTR_PHONE_ID = "attr_phoneid";
	public final static String ATTR_PHONE_LIST = "attr_phone_list";
	public final static String ATTR_PHONE_COPY = "attr_phone_copy";
	public final static String ATTR_MSG_PWD = "attr_msg_pwd";
	public final static String ATTR_ADDRESS = "attr_address";
	// 上级网机
	public final static String ATTR_PHONE_PARENT = "attr_phone_parent";
	
	/**
	 * 帐号相关
	 */
	public final static String ATTR_ACCOUNT = "attr_account";
	public final static String ATTR_RECHARGE_LIST = "attr_recharge_list";
	public final static String ATTR_CONSUME_LIST = "attr_consume_list";
	public final static String ATTR_START_DATE = "attr_start_date";
	public final static String ATTR_END_DATE = "attr_end_date";	
	
	/**
	 * 关键词
	 */
	public final static String ATTR_KEYS = "attr_keys";
	public final static String ATTR_KEY = "attr_key";
	
	/**
	 * 商品相关
	 */
	public final static String ATTR_PRODUCTS = "attr_products";
	public final static String ATTR_PRODUCT = "attr_product";
	
	/**
	 * 标定商品相关
	 */
	public final static String ATTR_STANDARD = "attr_standard";
	public final static String ATTR_STANDARDS = "attr_standards";
	
	/** 计算器状态 */
	public final static String ATTR_CALCULATOR_STATUS = "attr_calculator_status";
	public final static String ATTR_CALCULATOR_STATUS_SUN = "attr_calculator_status_sum";
	
	/**
	 * 计算器码表
	 */
	public final static String ATTR_CALCULATOR_CODE = "attr_calculator_code";
	public final static String ATTR_CALCULATOR_CODES = "attr_calculator_codes";
	public final static String ATTR_CALCULATOR_CODE_VALUE = "attr_calculator_code_value";
	public final static String ATTR_CALCULATOR_CODE_VALUES = "attr_calculator_code_values";

	
	/**
	 * 计算器商品
	 */
	public final static String ATTR_CALCULATOR_PRODUCT = "attr_calculator_product";
	public final static String ATTR_CALCULATOR_PRODUCT_ID = "calculator_product_id";
	public final static String ATTR_CALCULATOR_PRODUCT_NAME = "calculator_product_name";
	
	
	public final static String ATTR_CALCULATOR_PRODUCT_LIST = "attr_calculator_product_list";
	public final static String ATTR_CALCULATOR_ITEM_PRICE = "attr_calculator_item_price";
	public final static String ATTR_CALCULATOR_ITEM_PRICE_LIST = "attr_calculator_item_price_list";
	public final static String ATTR_CALCULATOR_ITEM_PRICE_DATA_LIST = "attr_calculator_item_price_data_list";
	public final static String ATTR_CALCULATOR_ITEM_USER_LIST = "attr_calculator_item_user_list";
	public final static String ATTR_CALCULATOR_PRODUCT_LIST_ALL = "attr_calculator_product_list_all";
	public final static String ATTR_CALCULATOR_CODE_AREA_LIST = "attr_calculator_code_area_list";
	public final static String ATTR_CALCULATOR_CODE_CURRENCY_LIST = "attr_calculator_code_currency_list";
	public final static String ATTR_CALCULATOR_CODE_DEBIT_LIST = "attr_calculator_code_debit_list";
	
	//计算器 是否权限
	public final static String CALCULATOR_STATUS_ON = "calculator_status_ok";//1
	
	//进销存 是否权限
	public final static String STORAGE_STATUS_ON = "storage_status_ok";//1
	
	//计算器list
	public final static String ATTR_CALCULATOR_FORMULA_LIST = "attr_calculator_formula_list";
	public final static String ATTR_CALCULATOR_FORMULA = "attr_calculator_formula";
	
	public final static String ATTR_CALCULATOR_CODE_DATASOURCE_LIST = "attr_calculator_code_data_source_list";
	public final static String ATTR_CALCULATOR_ITEM_USER_PARAMETER_LIST = "attr_calculator_item_user_parameter_list";//参数
	public final static String ATTR_CALCULATOR_ITEM_USER_DATA_LIST = "attr_calculator_item_user_data_list";//
	public final static String ATTR_CALCULATOR_ITEM_USER_TAX_LIST = "attr_calculator_item_user_tax_list";//税率
	public final static String ATTR_CALCULATOR_ITEM_USER_EXCHANGE_LIST = "attr_calculator_item_user_exchange_list";//汇率
	
	public final static String ATTR_CALCULATOR_ITEM_USER = "attr_calculator_item_user";
	public final static String ATTR_CALCULATOR_VALUE = "attr_calculator_value";
	
	
	public final static Integer ATTR_CALCULATOR_PRODUCT_STATUS_CANCEL = 0;//计算器_商品表 状态
	public final static Integer ATTR_CALCULATOR_PRODUCT_STATUS_OK = 1;//计算器_商品表 状态
	
	public final static String ATTR_CALCULATOR_PUBLIC_ID = "id";//
	public final static String ATTR_CALCULATOR_USER_DATA_ID = "user_data_id";//计算器_用户条款_数据ID
	public final static String ATTR_CALCULATOR_SITE_ID = "site_id";//计算器_来源网站ID
	
	public final static String ATTR_CALCULATOR_REPORT = "calculator_report";
	public final static String ATTR_CALCULATOR_REPORT_LIST = "calculator_report_list";

	/**
	 * 计算器系统税率 
	 */
	public final static String ATTR_CALCULATOR_TAX = "attr_calculator_tax";
	public final static String ATTR_CALCULATOR_TAXS = "attr_calculator_taxs";
	public final static String ATTR_CALCULATOR_TAX_DATA = "attr_calculator_tax_data";
	public final static String ATTR_CALCULATOR_TAX_DATAS = "attr_calculator_tax_datas";
	
	/**
	 * 计算器系统汇率
	 */
	public final static String ATTR_CALCULATOR_EXCHANGE = "attr_calculator_exchange";
	public final static String ATTR_CALCULATOR_EXCHANGES = "attr_calculator_exchanges";
	public final static String ATTR_CALCULATOR_EXCHANGE_DATA = "attr_calculator_exchange_data";
	public final static String ATTR_CALCULATOR_EXCHANGE_DATAS = "attr_calculator_exchange_datas";
	
	/**
	 * 计算器自动采集
	 */
	public final static String ATTR_CALCULATOR_COLLECTION_SITE = "attr_calculator_collection_site";
	public final static String ATTR_CALCULATOR_COLLECTION_SITES = "attr_calculator_collection_sites";
	public final static String ATTR_CALCULATOR_COLLECTION_PRODUCT = "attr_calculator_collection_product";
	public final static String ATTR_CALCULATOR_COLLECTION_PRODUCTS = "attr_calculator_collection_products";
	public final static String ATTR_CALCULATOR_COLLECTION_ITEM = "attr_calculator_collection_item";
	public final static String ATTR_CALCULATOR_COLLECTION_ITEMS = "attr_calculator_collection_items";
	public final static String ATTR_CALCULATOR_COLLECTION_ITEM_DATAS = "attr_calculator_collection_item_datas";
	
	/**
	 * 计算器
	 */
	public final static String ATTR_CALCULATOR = "attr_calculator";
	public final static String ATTR_CALCULATORS = "attr_calculators";
	
	/**
	 * 计算器图表
	 */
	public final static String ATTR_CALC_REPORT_CHART_LIST = "attr_calc_report_chart_list";
	public final static String ATTR_CALC_REPORT_CHART = "attr_calc_report_chart";
	public final static String ATTR_CALC_REPORT_CHART_CONF_LIST = "attr_calc_report_chart_conf_list";
	public final static String ATTR_CALC_REPORT_CHART_TYPE = "attr_calc_report_chart_type";
	
	/**
	 * 计算器自动采集账号
	 */
	public final static String ATTR_CALCULATOR_COLLECTION_ACCOUNTINFO_LIST = "attr_calculator_collection_accountinfo_list";
	public final static String ATTR_CALCULATOR_COLLECTION_ACCOUNTINFO = "attr_calculator_collection_accountinfo";
	
	/**
	 * 队列管理
	 */
	public final static String ATTR_CALCULATOR_QUEUES = "attr_calculator_queue";
	public final static String ATTR_CALCULATOR_CONSUMERSWITCH = "attr_calculator_switch";
	
	/**
	 * 计算器报表
	 */
	public final static String ATTR_CALCULATOR_REPORT_CONFIGURE_LIST = "attr_calculator_report_configure_list";
	public final static String ATTR_CALCULATOR_REPORT_CONFIGURE = "attr_calculator_report_configure";
	
	/**
	 * 商户管理
	 */
	public final static String ATTR_CRM_COMPANY_LIST = "attr_crm_company_list";
	public final static String ATTR_CRM_COMPANY = "attr_crm_company";
	
	/**
	 * 合同主体管理
	 */
	public final static String ATTR_CRM_CONTRACT_SUBJECT_LIST = "attr_crm_contract_subject_list";
	public final static String ATTR_CRM_CONTRACT_SUBJECT = "attr_crm_contract_subject";
	
	/**
	 * 计算器海关数据
	 */
	public final static String ATTR_CUSTOMS_DATA_LIST = "attr_customs_data_list";
	public final static String ATTR_CUSTOMS_DATA = "attr_customs_data";
	
	/**
	 * CMS 分类、标题、详情、推荐、 页脚
	 */
	public final static String ATTR_CMS_CATEGORY = "attr_cms_class";
	public final static String ATTR_CMS_CATEGORY_LIST = "attr_cms_classes";
	public final static String ATTR_CMS_ARTICLE = "attr_cms_article";
	public final static String ATTR_CMS_ARTICLE_LIST = "attr_cms_articles";
	public final static String ATTR_CMS_RECOMMEND = "attr_cms_recommend";
	public final static String ATTR_CMS_RECOMMEND_LIST = "attr_cms_recommends";
	public final static String ATTR_CMS_QUESTION_TYPE = "attr_cms_question_type";
	public final static String ATTR_CMS_QUESTION_TYPE_LIST = "attr_cms_question_types";
	public final static String ATTR_CMS_QUESTION = "attr_cms_question";
	public final static String ATTR_CMS_QUESTION_LIST = "attr_cms_questions";
	public final static String ATTR_CMS_FOOTER_LIST = "attr_cms_footer_list";
	public final static String ATTR_CMS_FOOTER = "attr_cms_footer";
	
	// 返回的咨询类型名称
	public final static String ATTR_CMS_TYPE_NAME = "attr_cms_type_name";
	// 判断用户是否登录
	public final static String ATTR_CMS_PHONEID = "attr_cms_phone_id";
	
	public final static String ATTR_CMS_INDEX = "首页";
	public final static Integer ATTR_CMS_QUESTION_ID = 5;
	
	/**
	 * 库存 --商品品牌
	 */
	public final static String ATTR_CRM_PRODUCT_BRAND_LIST = "attr_crm_product_brands";
	public final static String ATTR_CRM_PRODUCT_BRAND = "attr_crm_product_brand";

	/**
	 *	公司产品类型(买卖关系): 求购、销售 
	 */
	public final static Integer COMPANY_PRODUCIT_TYPE_BUY = 0;
	public final static Integer COMPANY_PRODUCIT_TYPE_SALE = 1;
	
	/**
	 * 
	 */
	public final static String ATTR_PHONE_PRODUCT ="attr_phone_product";
	public final static String ATTR_PHONE_BUY_LIST ="attr_phone_buy_list";
	public final static String ATTR_PHONE_SALE_LIST ="attr_phone_sale_list";
	public final static String ATTR_PHONE_ACCOUNT ="attr_phone_account";
	
	/**
	 * 性别
	 */
	public final static Integer USER_SEX_MAN = 0;	
	public final static Integer USER_SEX_WOMAN = 1;
	
	public final static Integer LINK_TYPE_CONTRACT = 0;//关联进项合同
	public final static Integer LINK_TYPE_STORE = 1;//关联到库区
	
	/**
	 * 搜索相关
	 */
	public final static String ATTR_QUERY_RESULT ="attr_query_result";
	
	public final static String FORM_TOKEN_KEY="form_token_key";
	public final static String FORM_PARAM_KEY="form_param_key";
	
	/**
	 * 国际化
	 */
	public final static String LOCALE_LANGEAGE="lang";
	public final static String LOCALE_COUNTRY="country";
	
	/**
	 * 关键字符过滤用
	 */
	/**
	 * This whitelist allows only text nodes: all HTML will be stripped.
	 */
	
	
	/**
	 *   This whitelist allows only simple text formatting: b, em, i, strong, u.
	 */
	//public final static Whitelist USER_CONTENT_FILTER_SIMPLETEXT = Whitelist.simpleText();
	/**
	 *  This whitelist allows the same text tags as basic(), and also allows img tags, with appropriate attributes, 
	 *  with src pointing to http or https.
	 */
	//public final static Whitelist USER_CONTENT_FILTER_BASICWITHIMAGES = Whitelist.basicWithImages();
	
	/**
	 *   This whitelist allows a full range of text and structural body HTML: 
	 *   	a, b, blockquote, br, caption, cite, code, col, colgroup, dd, dl, dt, em, 
	 *   	h1, h2, h3, h4, h5, h6, 
	 *   	i, img, li, ol, p, pre, q, small, strike, strong, sub, sup, 
	 *   	table, tbody, td, tfoot, th, thead, tr, u, ul 
	 *   
	 */
	//public final static Whitelist USER_CONTENT_FILTER_RELAXED = Whitelist.relaxed();
	/**
	 * 敏感字汇总
	 * 
	 */
	public final static String SENSITIVE_WORDS = "[法].{0,3}[轮];[江].{0,3}[泽].{0,3}[民];[Ff].{0,2}[Uu].{0,2}[Cc].{0,2}[Kk];[Ss].{0,2}[Hh].{0,2}[Ii].{0,2}[Tt];[六].{0,3}[四];[李].{0,3}[宏].{0,3}[志]";
	
	/**
	 * 敏感字符会自动被替换为**
	 */
	public final static String SENSITIVE_REPLACE_WORDS = "**";
	
	//网机找回密码时候激活码的使用期限(单位:天)
	public final static int ADDRESS_ACTIVE_EXPIRE_DAY = 1;	

	public final static int COMPANY_TYPE_TRAFFICKER = 1;//贸易商
	public final static int COMPANY_TYPE_PRODUCER = 2;//生产商
	public final static int COMPANY_TYPE_BOTH = 3;//生产+贸易
	public final static int COMPANY_TYPE_OTHER = 4;//其他
	
	//复印件保存路径（路径要配置在）
	public final static String PHONE_COMPANY_COPY_PATH = "/uploads/company";
	public final static String PHONE_PERSON_COPY_PATH = "/uploads/person";
	public final static String PHONE_PORTRAIT_PATH = "/uploads/avatar";
	//公司logo
	public final static String COMPANY_LOGO_PATH = "/uploads/logo";
	//公司附件路径
	public final static String COMPANY_ATTACHEMENT ="/uploads/attachement/{0}";
	
	
	
	//网机头像尺寸
	//小头像 50*50
	public final static int PHONE_AVATAR_SMALL_WIDTH = 50;
	public final static int PHONE_AVATAR_SMALL_HEIGHT = 50;

	//中型头像 70*80
	public final static int PHONE_AVATAR_MIDDLE_WIDTH = 70;
	public final static int PHONE_AVATAR_MIDDLE_HEIGHT = 80;

	//小头像 100*120
	public final static int PHONE_AVATAR_BIG_WIDTH = 100;
	public final static int PHONE_AVATAR_BIG_HEIGHT = 120;
	
	//计算器码表类型
	public final static int CALCULATOR_CODE_TYPE_AREA = 1; //区域
	public final static int CALCULATOR_CODE_TYPE_CURRENCY = 2; //币种
	public final static int CALCULATOR_CODE_TYPE_PARAMETER = 3; //参数
	public final static int CALCULATOR_CODE_TYPE_TAX = 4; //税率类型
	public final static int CALC_CODE_TYPE_COLLECT = 5;//采集类型
	public final static int CALC_CODE_TYPE_DEBIT = 6;//借贷
	public final static int CALC_CODE_TYPE_PAY = 7;//结算方式
	
	//采集类型-值
	public final static int CALC_CODE_TYPE_COLLECT_ID =1;
	public final static int CALC_CODE_TYPE_COLLECT_ATTR =2;
	public final static int CALC_CODE_TYPE_COLLECT_TAG =3;
	public final static int CALC_CODE_TYPE_COLLECT_SEL =4;
	public final static int CALC_CODE_TYPE_COLLECT_REG =5;
	
	//用户参数分类
	public final static Integer CALCULATOR_ITEM_USER_PARAMETER = 0;//参数
	public final static Integer CALCULATOR_ITEM_USER_TAX = 1;//税率
	public final static Integer CALCULATOR_ITEM_PRICE = 2;//价格条款
	
	//计算器变量符号
	public final static String CALCULATOR_SIGN_TAX = "tax"; //税率条款-用户
	public final static String CALCULATOR_SIGN_SYSTAX = "sysTax"; //系统税率-系统
	
	public final static String CALCULATOR_SIGN_P = "p"; //价格条款-用户
	public final static String CALCULATOR_SIGN_SYSP = "sysP"; //价格条款-系统
	public final static String CALCULATOR_SIGN_V = "v"; //参数条款-用户
	public final static String CALCULATOR_SIGN_SYSV = "sysV"; //参数条款-系统
	
	public final static String CALCULATOR_SIGN_SYSEX = "sysEx"; //系统汇率-系统
	public final static String CALCULATOR_SIGN_INITVALUE = "1"; //符号初始值
	public final static int CALCULATOR_SIGN_ADDVALUE = 1; //符号递增值
	
	//价格条款数据来源方式
	public final static Integer CALCULATOR_ITEM_PRICE_SHOU = 0;//手工录入
	public final static Integer CALCULATOR_ITEM_PRICE_CAIJI = 1;//自动采集
	public final static Integer CALCULATOR_ITEM_PRICE_GONGSHI = 2;//公式计算
	
	//自动采集 异步取数据时的返回状态
	public final static Integer CALCULATOR_COLLECT_NOZHANGHAO = 1;//没有账号
	public final static Integer CALCULATOR_COLLECT_NOPRODUCT = 2;//没有商品
	public final static Integer CALCULATOR_COLLECT_HASPRODUCT = 3;//自动采集
	
	public final static Integer COLLE_SITE_FANGDO = 1; //采集方都网
	public final static Integer COLLE_SITE_ICIS = 2; //易贸
	public final static Integer COLLE_SITE_CHEM365 = 3; //中宇
	public final static Integer COLLE_SITE_SCI99 = 4; //卓创资讯
	
	//后台分页
	public final static int Background_PAGE_PER_LARGE = 500;
	public final static int Background_PAGE_PER_SMALL = 100;
	
	//采集的数据使用高值还是使用低值 
	public final static Boolean CALC_COLLECT_USE_HIGHT_VALUE = Boolean.TRUE;
	public final static Boolean CALC_COLLECT_USE_LOW_VALUE = Boolean.FALSE;
	
	//报表
	public final static String ATTR_CALC_REPORT_SHOW_URL = "attr_calc_report_showurl";//报表ID
	public final static String ATTR_CALC_REPORT_START = "attr_calc_report_startdate";
	public final static String ATTR_CALC_REPORT_END = "attr_calc_report_enddate";
	
	// 报表类型
	public final static Integer CALC_REPORT_CYCLE_DAY = 0; // 日报
	public final static Integer CALC_REPORT_CYCLE_WEEK_LAST = 1; // 周报last
	public final static Integer CALC_REPORT_CYCLE_WEEK_AVG = 2; // 周报avg
	public final static Integer CALC_REPORT_CYCLE_MONTH_LAST = 3; // 月报last
	public final static Integer CALC_REPORT_CYCLE_MONTH_AVG = 4; // 月报last
	
	public final static Integer CALC_REPORT_TYPE_DATA = 0; // 数据表
	public final static Integer CALC_REPORT_TYPE_COMPARE = 0; // 数据对比表
	
	//商户管理 删除标记
	public final static Integer CRM_COMPANY_DELETE_MARK_NO = 0;//未删除
	public final static Integer CRM_COMPANY_DELETE_MARK_OK = 1;//已删除
	
	//检测是否包含关系
	public final static String CHECK_INCLUDE = "check_include";
	
	// 是否撮合
	public final static Integer ATTR_NOT_MATCH = 0; 
	public final static Integer ATTR_MATCH = 1; 
	// 成交动作类型
	public final static Integer ATTR_DEAL_KIND = 3; // 接受成交
	// 撮合状态
	public final static Integer ATTR_MATCH_STATUS = 3; //撮合
	
	// 分销系统客户类型
	public final static Integer ATTR_CRM_COMPANY_A = 1; // 销售商
	public final static Integer ATTR_CRM_COMPANY_S = 2; // 销售商&客户
	public final static Integer ATTR_CRM_COMPANY_T = 3; // 客户
	
	// 是否已签约
	public final static Integer ATTR_CRM_SIGN = 1; // 是
	public final static Integer ATTR_CRM_NOT_SIGN = 0; // 否
	
	// 是否合同框架
	public final static Integer ATTR_CRM_FRAMEWORK = 1; // 是
	public final static Integer ATTR_CRM_NOT_FRAMEWORK = 0; // 否
	
	// 商户-是否已删除
	public final static Integer ATTR_CRM_COMPANY_ON = 0; // 未删除
	public final static Integer ATTR_CRM_COMPANY_OFF = 1; // 已删除
	
	public final static Integer ATTR_MATCH_WEB_ON = 1; // 未删除
	public final static Integer ATTR_MATCH_WEB_OFF = 0; // 已删除
	
	// 支付状态
	public final static Integer ATTR_PAY_NO = 0; // 未支付
	public final static Integer ATTR_PAY_YES = 1; // 已支付
	public final static Integer ATTR_PAY_CHECK = 2; // 工作流审核
	public final static Integer ATTR_PAY_QUXIAO = 3; //取消申请
	
	// 进销存参数
	public final static String ATTR_CRM_PARAM_LIST = "attr_crm_param_list";
	public final static String ATTR_CRM_PARAM = "attr_crm_param";
	
	// 库区
	public final static String ATTR_CRM_STORE_LIST = "attr_crm_store_list";
	public final static String ATTR_CRM_STORE = "attr_crm_store";
	
	// 默认参数值
	public final static Integer ATTR_CRM_DEFAULT_NUM = 7;
	
	// 参数初始化
	public final static String ATTR_CRM_PRE_SIGNED_DATE_WARN = "attr_pre_signed_date_warn"; // 合同预计签订日期预警天数
	public final static String ATTR_CRM_DELIVERY_DATE_WARN = "attr_delivery_date_warn"; // 提货日期预警天数
	public final static String ATTR_CRM_PAYMENT_DATE_WARN = "attr_payment_date_warn"; // 合同付款日期预警天数
	public final static String ATTR_CRM_BACK_DATE_WARN = "attr_back_date_warn"; // 合同回款日期预警天数
	public final static String ATTR_CRM_PRECURSOR_DATE_WARN = "attr_precursor_date_warn"; // 易制毒办理日期预警天数
	
	// 参数初始化
	public final static String ATTR_CRM_PRE_SIGNED_DATE_WARN_CH = "合同预计签订日期——预警天数";
	public final static String ATTR_CRM_PAYMENT_DATE_WARN_CH =    "进项合同付款日期——预警天数";
	public final static String ATTR_CRM_BACK_DATE_WARN_CH =       "销项合同回款日期——预警天数";
	public final static String ATTR_CRM_DELIVERY_DATE_WARN_CH =   "销项合同预计提货日期——预警天数";
	public final static String ATTR_CRM_PRECURSOR_DATE_WARN_CH =  "易制毒办理日期——预警天数";
	
	// 后台 - 进销存管理
	public final static String ATTR_STORAGE_LIST = "attr_storage_list";
	public final static String ATTR_STORAGE = "attr_storage";
	
	//扣款
	public final static Integer ATTR_DEBIT_MATCH = 1;
	public final static Integer ATTR_DEBIT_TRADE = 2;
	public final static Integer ATTR_DEBIT_CAL = 3;
	public final static Integer ATTR_DEBIT_STORAGE = 4;
	public final static Integer ATTR_DEBIT_ADVERTISEMENT = 5;
	
	public static String[] ATTR_DEBIT_TOTAL = {"","撮合","磋商","计算器","进销存","广告"};
	
	//单独扣款jsp页面返回的数据
	public final static String ATTR_DEBIT_ADVERTISEMENT_RESULT = "debit_adver_result";
	
	// 区域列表
	public final static String ATTR_CRM_COMPANY_AREA_LIST = "company_area_list";
	
	// 进销存开通状态统计
	public static final String ATTR_STORAGE_STATUS = "storage_status";
	// 进销存开通状态和
	public static final String ATTR_STORAGE_STATUS_SUM = "storage_status_sum";
	// 商品数量map
	public static final String ATTR_PRODUCT_NUM_MAP = "product_num_map";
	// 计算器开通商品是否超过规定值 true-超过
	public static final String ATTR_IS_CLAC_NUM_OVER = "is_calc_num_over";
	// 进销存开通商品是否超过规定值 true-超过
	public static final String ATTR_IS_STORAGE_NUM_OVER = "is_storage_num_over";
	
	public static final String ATTR_PRODUCT_TYPE = "product_type";
	
	// 开票
	public static final String ATTR_CRM_CONTRACT_INVOICE_LIST = "attr_crm_contract_invoice_list";
	public static final String ATTR_CRM_CONTRACT_INVOICE = "attr_crm_contract_invoice";
	
	//附件Attachment
	public static final String ATTR_CRM_CONTRACT_ATTACHMENT_LIST = "attr_crm_contract_attachment_list";
	public static final String ATTR_CRM_CONTRACT_ATTACHMENT = "attr_crm_contract_attachment";
	// 单号参数
	public static final String CRM_NUMBER_VALUE1_TYPE = "yyMM";
	public static final String CRM_NUMBER_VALUE2_LENGTH = "%04d";
	public static final String CRM_NUMBER_CONTRACT_SIGN = "-";
	// 单号类型
	//public static final String CRM_NUMBER_HT = "HT"; // 合同
	public static final String CRM_NUMBER_JXHT = "JXHT"; // 进项合同
	public static final String CRM_NUMBER_XXHT = "XXHT"; // 销项合同
	public static final String CRM_NUMBER_THD = "TH"; // 提货单
	
	
	//角色
	public static final String ATTR_ROLE_LIST = "roles";
	public static final String ATTR_ROLE = "role";
	
	//角色网机关联
	public static final String ATTR_ROLE_USER_LIST = "role_users";
	public static final String ATTR_ROLE_USER = "role_user";
	
	public static final String ATTR_SYSMENU_LIST = "sys_menu_list";
	public static final String ATTR_SYSMENU_STR = "sys_menu_STR";
	
	
	//工作流
	public final static String ATTR_FW_ERROR_MSG ="flowwork_error_msg";
	
	public static final String ATTR_FW_START ="flowwork_start";
	public static final String ATTR_FW_START_LIST ="flowwork_start_list";
	public static final String ATTR_FW_ROLE_LIST ="flowwork_role_list";
	
	public static final String ATTR_FW_DE_TASK_LIST ="flowwork_de_task_list";
	
	public static final String ATTR_FW_PD_DP_LIST ="ProcessDefinition_deployment_list";
	public static final String ATTR_FW_CRM_CONTACT_LIST ="crm_contant_list";
	public static final String ATTR_FW_CRM_CONTACT ="crm_contant";
	public static final String ATTR_FW_PROCESSDEFINITION_LIST  = "ProcessDefinition_list";
	public final static String ATTR_FW_PROCESSDEFINITION_BPMN ="uploads"+File.separator+"activiti";
	
	//
	public final static String ATTR_CRM_CONTRACT_BUY_TMP_LIST = "attr_crm_contact_buy_temp_list";
	
	public final static String ATTR_CONTRACT_BUY = "P";//进项合同 【】开票、付款
	public final static String ATTR_CONTRACT_SALE = "R";//销项合同
	
	public final static Integer ATTR_CONTRACT_PAY_NOREFUND = 0;//销项合同  不是退款/退货
	public final static Integer ATTR_CONTRACT_PAY_REFUND = 1;//销项合同退款/退货
	
	public final static Integer ATTR_PRECURSOR_CHEMICAL_APPLYING = 1;//申请中
	public final static Integer ATTR_PRECURSOR_CHEMICAL_APPLIED = 2;//申请完成
	
	public final static Integer ATTR_ISTRANSPORT_NO = 0;//无运输
	public final static Integer ATTR_ISTRANSPORT_YES = 1;//有运输
	
	public final static String ATTR_FLOW_TASK_INSPECTION = "precursorTask";//工作流中特殊任务的名称 易制毒
	
	
	//进销项付款中的款项类型
	public static Map<Integer, String> fundTypes = null;
	public static final Integer FUND_TYPE_SHANGPIN = 1;
	public static Map<Integer, String> getFundType (){
		if(fundTypes==null){
			fundTypes = new HashMap<Integer, String>();
			fundTypes.put(FUND_TYPE_SHANGPIN, "商品");
			fundTypes.put(2, "运输");
			fundTypes.put(3, "包装");
			fundTypes.put(4, "其他");
		}
		return fundTypes;
	}
	public static String getFundType(Integer key){
		getFundType ();
		return fundTypes.get(key);
	}
	
	//销项开票中发票类型
	public static Map<Integer, String> invoiceTypes = null;
	public static final Integer INVOICE_TYPE_SHANGPIN = 1;
	public static Map<Integer, String> getInvoiceType (){
		if(invoiceTypes==null){
			invoiceTypes = new HashMap<Integer, String>();
			invoiceTypes.put(1, "商品增值税");
			invoiceTypes.put(2, "运输发票");
			invoiceTypes.put(3, "包装发票");
			invoiceTypes.put(4, "其他");
		}
		return invoiceTypes;
	}
	public static String getInvoiceType(Integer key){
		getInvoiceType ();
		return invoiceTypes.get(key);
	}

	//工作流类型
	private static HashMap<String, String> flow_type = null;
	public static final String FLOW_TYPE_JIN_HETONG = "1";
	public static final String FLOW_TYPE_JIN_RUKU = "2";
	public static final String FLOW_TYPE_JIN_FUKUAN = "3";
	public static final String FLOW_TYPE_JIN_FEIFUKUAN = "4";
	public static final String FLOW_TYPE_XIAO_HETONG = "5";
	public static final String FLOW_TYPE_XIAO_CHUKU = "6";
	public static final String FLOW_TYPE_XIAO_TUIHUORUKU = "7";
	public static final String FLOW_TYPE_XIAO_KAIPIAO = "8";
	public static final String FLOW_TYPE_XIAO_TUIKUAN = "9";
	public static final String FLOW_TYPE_XIAO_FEIFUKUAN = "10";
	public static List<String> getFlowTypeKey(){
		List<String> list = new ArrayList<String>();
		list.add(FLOW_TYPE_JIN_HETONG);
		list.add(FLOW_TYPE_XIAO_HETONG);
		list.add(FLOW_TYPE_JIN_FUKUAN);
		list.add(FLOW_TYPE_JIN_RUKU);
		list.add(FLOW_TYPE_XIAO_CHUKU);
		list.add(FLOW_TYPE_XIAO_TUIHUORUKU);
		list.add(FLOW_TYPE_XIAO_KAIPIAO);
		list.add(FLOW_TYPE_XIAO_TUIKUAN);
		list.add(FLOW_TYPE_JIN_FEIFUKUAN);
		list.add(FLOW_TYPE_XIAO_FEIFUKUAN);
		return list;
	}
	public static HashMap<String, String> getFlowType(){
		if(flow_type==null){
			flow_type = new HashMap<String, String>();
			flow_type.put(FLOW_TYPE_JIN_HETONG, "进项合同");
			flow_type.put(FLOW_TYPE_XIAO_HETONG, "销项合同");
			flow_type.put(FLOW_TYPE_JIN_FUKUAN, "进项付款");
			flow_type.put(FLOW_TYPE_JIN_RUKU, "进项入库");
			flow_type.put(FLOW_TYPE_XIAO_CHUKU, "销项出库");
			flow_type.put(FLOW_TYPE_XIAO_TUIHUORUKU, "销项退货入库");
			flow_type.put(FLOW_TYPE_XIAO_KAIPIAO, "销项开票");
			flow_type.put(FLOW_TYPE_XIAO_TUIKUAN, "销项退款");
			flow_type.put(FLOW_TYPE_JIN_FEIFUKUAN, "进项费用付款");
			flow_type.put(FLOW_TYPE_XIAO_FEIFUKUAN, "销项费用付款");
		}
		return flow_type;
	}
	public static String getFlowType(String key){
		if(key==null){
			return null;
		}
		return getFlowType().get(key);
	}
	
	public final static Integer ATTR_CONTRACT_SUNHAO_COMPANY = 1; //损耗承担类型-公司
	public final static Integer ATTR_CONTRACT_SUNHAO_PERSONAL = 2;//损耗承担类型-个人
	public final static Integer ATTR_CONTRACT_SUNHAO_LOGISTICS = 3;//损耗承担类型-物流
	public final static Integer ATTR_CONTRACT_SUNHAO_OTHER=4;//损耗承担类型-对方
	private static HashMap<Integer, String> sunhao_type = null;
	public static HashMap<Integer, String> getSunhaoType(){
		if(sunhao_type==null){
			sunhao_type = new HashMap<Integer, String>();
			sunhao_type.put(ATTR_CONTRACT_SUNHAO_COMPANY, "公司");
			sunhao_type.put(ATTR_CONTRACT_SUNHAO_PERSONAL, "个人");
			sunhao_type.put(ATTR_CONTRACT_SUNHAO_LOGISTICS, "物流");
//			sunhao_type.put(ATTR_CONTRACT_SUNHAO_OTHER, "对方");
		}
		return sunhao_type;
	}
	public static String getSunhaoType(Integer key){
		if(key==null){
			return null;
		}
		return getSunhaoType().get(key);
	}
	
	public static List<String> change_type = null;
	public static List<String> getChangeType(){
		if(change_type==null){
			change_type = new ArrayList<String>();
			change_type.add("货权");
			change_type.add("不可撤销");
			change_type.add("介绍信");
			change_type.add("车号");
			change_type.add("船号");
			change_type.add("现场交割");
			change_type.add("提单");
		}
		return change_type;
	}
	
	public static List<String> transaction_mode = null;
	public static List<String> getTransactionmode(){
		if(transaction_mode==null){
			transaction_mode = new ArrayList<String>();
			transaction_mode.add("自采");
			transaction_mode.add("调拨");
			transaction_mode.add("合约");
			transaction_mode.add("期货");
		}
		return transaction_mode;
	}

}

