import { Component, OnInit } from '@angular/core';
import { User } from '../../_models/user';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { UsersService } from '../../_services/users.service';
import { AuthService } from '../../_services/auth.service';
@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.scss']
})
export class ProfilesComponent implements OnInit {

  currentUser: User;
  userUpdate: User;
  profileForm  = new FormGroup({
    manhanvien: new FormControl({value: '', disabled: true}),
    password:  new FormControl('', Validators.required),
    ten:  new FormControl('', Validators.required),
    sdt:  new FormControl(''),
    email:  new FormControl(''),
    diachi:  new FormControl(''),
    gioitinh:  new FormControl(''),
    ngaysinh:  new FormControl(''),
  });
  constructor( private userService: UsersService, private authService: AuthService) {}

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.userUpdate = this.currentUser;
    this.updateValueProfileForm();
    // this.userService.getUserByMaNV(this.currentUser.manhanvien).subscribe({
    //   next(num) { console.log(num); },
    //   complete() { console.log('Finished sequence'); }
    // });

  }

  updateValueProfileForm() {
    this.profileForm.controls['manhanvien'].setValue(this.currentUser.manhanvien);
    this.profileForm.controls['ten'].setValue(this.currentUser.ten);
    this.profileForm.controls['sdt'].setValue(this.currentUser.sdt);
    this.profileForm.controls['email'].setValue(this.currentUser.email);
    this.profileForm.controls['diachi'].setValue(this.currentUser.diachi);
    this.profileForm.controls['gioitinh'].setValue(this.currentUser.gioitinh ? 1 : 0);
    this.profileForm.controls['ngaysinh'].setValue(this.currentUser.ngaysinh);
  }



  updateUser() {
    this.userUpdate.manhanvien = this.currentUser.manhanvien;
    this.userUpdate.ten = this.profileForm.controls['ten'].value;
    this.userUpdate.sdt = this.profileForm.controls['sdt'].value;
    this.userUpdate.email = this.profileForm.controls['email'].value;
    this.userUpdate.diachi = this.profileForm.controls['diachi'].value;
    this.userUpdate.gioitinh = this.profileForm.controls['gioitinh'].value;
    this.userUpdate.ngaysinh = this.profileForm.controls['ngaysinh'].value;
    this.userUpdate.password = this.profileForm.controls['password'].value;
    this.userService.updateUser(this.userUpdate).subscribe(next => {
      this.currentUser = this.userUpdate;
      alert('Update Successfully');
    }, error => {
      alert('Login Fail');
    }, () => {});
  }
}
