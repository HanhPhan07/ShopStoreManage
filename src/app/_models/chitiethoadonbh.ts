import { SanPham } from './sanpham';
import { HoaDonBanHang } from './hoadonbanhang';

export interface ChiTietHoaDonBH {
  id: number;
  hoadonbanhang: HoaDonBanHang;
  sanpham: SanPham;
  soluong: number;
  gia: number;
  giamgia: number;
}
