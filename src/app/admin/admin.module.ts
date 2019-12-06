import { NgModule  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { PaginationModule } from 'ngx-bootstrap/pagination';

import { AdminComponent } from './admin.component';
import { HeaderComponent } from './layout/header/header.component';
import { AdminRoutingModule } from './admin-routing.module';
import { ControlSidebarComponent } from './layout/control-sidebar/control-sidebar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { ContentComponent } from './layout/content/content.component';
import { LeftSideComponent } from './layout/left-side/left-side.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UsersComponent } from './users/users.component';
import { UserDetailComponent } from './users/user-detail/user-detail.component';
import { PurchaseHistoryComponent } from './users/user-detail/purchase-history/purchase-history.component';
import { DebtComponent } from './users/user-detail/debt/debt.component';
import { OrdersComponent } from './orders/orders.component';
import { SellProdComponent } from './orders/sell-prod/sell-prod.component';
import { ProductsComponent } from './products/products.component';
import { CategoriesProdComponent } from './products/categories-prod/categories-prod.component';
import { ManufProdComponent } from './products/manuf-prod/manuf-prod.component';
import { SuppliersProdComponent } from './products/suppliers-prod/suppliers-prod.component';
import { ProfilesComponent } from './profiles/profiles.component';

@NgModule({
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BsDatepickerModule.forRoot(),
    PaginationModule.forRoot()
  ],
  declarations: [
    AdminComponent,
    HeaderComponent,
    LeftSideComponent,
    ContentComponent,
    FooterComponent,
    ControlSidebarComponent,
    DashboardComponent,
    UsersComponent,
    UserDetailComponent,
    PurchaseHistoryComponent,
    DebtComponent,
    OrdersComponent,
    SellProdComponent,
    ProductsComponent,
    CategoriesProdComponent,
    ManufProdComponent,
    SuppliersProdComponent,
    ProfilesComponent
  ]
})
export class AdminModule {}
