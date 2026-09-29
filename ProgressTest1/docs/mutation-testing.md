# Báo cáo JaCoCo Coverage & Mutation Testing

## 1. Kết quả JaCoCo Coverage

Chạy kiểm thử: `mvn clean test`  
Báo cáo HTML: `target/site/jacoco/index.html`

| Lớp | Line Coverage | Yêu cầu | Branch Coverage | Yêu cầu | Đánh giá |
|---|---|---|---|---|---|
| `AccountValidator` | **100%** (31/31) | ≥ 80% | **78.0%** (39/50) | ≥ 70% | ĐẠT |
| `AccountService` | **100%** (118/118) | ≥ 80% | **94.9%** (93/98) | ≥ 70% | ĐẠT |
| **Tổng thể (Core)** | **100%** (149/149) | **≥ 80%** | **89.2%** (132/148) | **≥ 70%** | **VƯỢT CHỈ TIÊU** |

Tổng số test: **118/118 tests pass** (0 failures, 0 errors, 0 skipped).

---

## 2. Bảng Mutation Testing thủ công (3 lỗi giả lập)

| # | Vị trí | Lỗi giả lập (Mutation) | Test phát hiện (Test Fail) | Trạng thái hoàn tác |
|---|---|---|---|---|
| **M1** | `AccountService.login()` | Đổi `>= MAX_FAILED_ATTEMPTS` thành `> MAX_FAILED_ATTEMPTS` (sai 5 lần không khóa ngay) | `AccountServiceTest$Login.login_WrongPassword5thTime_LocksAccount` | ✅ Đã hoàn tác |
| **M2** | `AccountService.login()` | Bỏ nhánh kiểm tra đang khóa `if (account.isLocked())` | `AccountServiceTest$Login.login_WhileLocked_RejectsWithoutIncrement` | ✅ Đã hoàn tác |
| **M3** | `AccountValidator` | Sửa regex username từ `{4,19}` thành `{4,20}` (chấp nhận độ dài 21) | `AccountValidatorTest.isValidUsername_BoundaryLength` (biên độ dài 21) | ✅ Đã hoàn tác |

**Kết luận:** Cả 3/3 lỗi giả lập đều bị bộ kiểm thử bắt được ngay lập tức (Mutation Score = 100%). Mã nguồn đã được hoàn tác về trạng thái ban đầu sạch sẽ và toàn bộ kiểm thử chạy xanh.
