package com.globits.da.validate;

public enum ResponseStatus {
    // NULL
    PROVINCE_CODE_IS_NULL(40011, "Mã 'Tỉnh' không được null"),

    DISTRICT_CODE_IS_NULL(40012, "Mã 'Huyện' không được null"),

    VILLAGE_CODE_IS_NULL(40012, "Mã 'Xã' không được null"),

    EMPLOYEE_CODE_IS_NULL(40014, "Mã 'Nhân viên' không được null"),

    CERTIFICATE_CODE_IS_NULL(40015, "Mã 'Văn Bằng' không được null"),

    PROVINCE_ID_IS_NULL(40016, "Id của 'tỉnh' không được null"),

    DISTRICT_ID_IS_NULL(40017, "Id của 'huyện' không được null"),

    VILLAGE_ID_IS_NULL(40018, "Id của 'xã' không được null"),

    PROVINCE_NAME_IS_NULL(40019, "Tên 'Tỉnh' không được null"),

    DISTRICT_NAME_IS_NULL(40020, "Tên 'Huyện' không được null"),

    VILLAGES_NAME_IS_NULL(40021, "Tên 'Xã' không được null"),

    EMPLOYEE_NAME_IS_NULL(40022, "Tên 'Nhân Viên' không được null"),

    CERTIFICATE_NAME_IS_NULL(40023, "Tên 'Văn Bằng' không được null"),

    EMPLOYEE_MAIL_IS_NULL(40024, "Email không được null"),

    PHONE_IS_NULL(40025, "Số điện thoại không được null"),

    AGE_IS_NULL(40025, "Tuổi không được null"),

    EMPLOYE_ID_IS_NULL(40025, "Id 'Nhân Viên' không được null"),

    CERTIFICATE_ID_IS_NULL(40028, "Id văn bằng không được null"),

    EXPIRATION_IS_NULL(40029, "Ngày kết thúc không được null"),

    EFECTIVE_IS_NULL(40030, "Ngày bắt đầu không được null"),

    CELL_IS_NULL(40031, "Có thông tin bị bỏ trống."),

    AGE_DATA_TYPE_WRONG(40032, "Tuổi nhập phải là số."),


    // EXIST

    CODE_IS_EXIST(40041, "Mã đã tồn tại."),

    PROVINCE_CODE_IS_EXIST(40042, "Mã 'Tỉnh' đã tồn tại."),

    DISTRICT_CODE_IS_EXIST(40043, "Mã 'Huyện' đã tồn tại."),

    DISTRICT_CODE_DUPLICATE(4003, "Mã 'Xã' vừa nhập trùng nhau"),

    VILLAGES_CODE_IS_EXIST(40044, "Mã 'Xã' đã tồn tại."),

    VILLAGES_CODE_DUPLICATE(4004, "Mã 'Xã' vừa nhập trùng nhau"),

    EMPLOYEE_CODE_IS_EXIST(40045, "Mã 'Nhân viên' đã tồn tại."),

    EXCEL_EMPLOYEE_CODE_IS_EXIST(40045, "Trong file excel có Mã 'Nhân viên' giống nhau."),

    CERTIFICATE_CODE_IS_EXIST(40046, "Mã 'Văn Bằng' đã tồn tại."),

    EXCEL_ERROR(40064, ""),


    // NOT EXIST,

    PROVINCE_NOT_EXIST(40051, "Id của 'tỉnh' không tồn tại"),

    NOT_DISTRICT_EXIST(40052, "Không có huyện nào tồn tại thuộc tỉnh"),

    DISTRICT_NOT_EXIST(40053, "Id của 'huyện' không tồn tại"),

    VILLAGE_NOT_EXIST(40054, "Id của 'xã' không tồn tại"),

    EMPLOYEE_ID_NO_EXIST(40055, "Id nhân viên không tồn tại"),

    CERTIFICATE_ID_NO_EXIST(40056, "Mã văn bằng không tồn tại"),

    EMPLOYEE_CERTIFICATE_ID_NO_EXIST(40057, "Id chứng thực văn bằng của nhân viên không tồn tại"),

    NOT_EMPLOYEE_EXIST(40058, "Không có nhân viên nào tồn tại"),


    //WRONG_FORMAT
    NON_PROVINCAL_DISTRICT(40071, "Huyện không thuộc Tỉnh"),

    NON_DISTRIC_VILLAGE(40072, "Xã không thuộc Huyện"),

    EMPLOYEE_CODE_WRONG_FORMAT(40073, "Mã sai định dạnh (không dấu 'cách', độ dài 6-10 ký tự)"),

    EMAIL_WRONG_FORMAT(40074, "EMAIL sai định dạnh"),

    PHONE_WRONG_FORMAT(40075, "Số điện thoại sai định dạnh (11 số)"),

    AGE_WRONG_FORMAT(40076, "Tuổi không âm"),

    CONFLICT(40077, "Xung đột xảy ra với dữ liệu nhập vào"),

    EXPIRATION_EFFECTIVE_ERROR(40078, "Ngày kết thúc phải sau ngày bắt đầu có hiệu lực"),

    EXPIRATION_NOW_ERROR(40079, "Ngày kết thúc phải sau ngày tạo hiện tại"),

    HAVE_3_CERTIFICATE(40080, "Đã có 3 văn bằng cùng loại vẫn còn hạn dùng, không thể thêm nữa"),

    CERTIFICATE_ALREADY_EXIST(40081, "Nhân viên này đã có văn bằng này."),

    FILE_ERROR(400, "File gửi lên sai. [code, name, age, email, phone, province code, district coe, VILLAGE code]"),

    PROVINCE_AREA_ERROR(40082, "Diện tích 'Tỉnh' sai định dạng (không được bỏ trống, không được âm)"),

    DISTRICT_AREA_ERROR(40083, "Diện tích 'Huyện' sai định dạng (không được bỏ trống, không được âm)"),

    VILLAGES_AREA_ERROR(400, "Diện tích 'Xã' sai định dạng (không được bỏ trống, không được âm)"),

    DATE_WRONG_FORMAT(40084, "Ngày nhập không đúng định dạng (yyyy-MM-dd)"),

    PROVINCE_ID_ERROR(40086, "Id của tỉnh đang sai."),

    DISTRICT_ID_ERROR(40087, "Id của huyện đang sai."),

    VILLAGES_ID_ERROR(40088, "Id của xã đang sai."),


    //Eidt
    NEDD_TO_EDIT_PROVINCE_DISTRICT_VILLAGE(40091, "Cần sửa cả thông tin tỉnh, huyện, xã nếu muốn thay đổi địa chỉ"),


    // Success

    SUCCESS(200, "Thành công!"),
    ;
    private final int code;
    private String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
