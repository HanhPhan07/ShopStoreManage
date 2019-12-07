import {KhachHang} from './khachhang';
import {User} from './user';
import { ChiTietHoaDonBH } from './chitiethoadonbh';
export class HoaDonBanHang {
  id: number;
  mahoadon?: string;
  khachhang: KhachHang;
  loaithanhtoan: number;
  tonggia: number;
  giamgia?: number;
  khachhangtra: number;
  trangthai?: number;
  createdAt: Date;
  updatedAt?: Date;
  nguoisua?: User;
  nguoitao: User;
  chitiethoadons?: ChiTietHoaDonBH[];
}
