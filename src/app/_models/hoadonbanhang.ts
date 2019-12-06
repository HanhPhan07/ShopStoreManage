import {KhachHang} from './khachhang';
import {User} from './user';
export interface HoaDonBanHang {
  id: number;
  mahoadon: string;
  khachhang: KhachHang;
  loaithanhtoan: number;
  tonggia: number;
  giamgia?: number;
  khachhangtra: number;
  trangthai: number;
  createdAt: Date;
  updatedAt?: Date;
  nguoisua?: User;
  nguoitao: User;
  chitiethoadons: string;
}
