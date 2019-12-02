import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UsersComponent } from './users/users.component';
import { UserDetailComponent } from './users/user-detail/user-detail.component';
import { OrdersComponent } from './orders/orders.component';
import { SellProdComponent } from './orders/sell-prod/sell-prod.component';
import { ProductsComponent } from './products/products.component';
import { CategoriesProdComponent } from './products/categories-prod/categories-prod.component';
import { ManufProdComponent } from './products/manuf-prod/manuf-prod.component';
import { SuppliersProdComponent } from './products/suppliers-prod/suppliers-prod.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'user/:id',
        component: UserDetailComponent
      },
      {
        path: 'orders',
        component: OrdersComponent
      },
      {
        path: 'sell-prod',
        component: SellProdComponent
      },
      {
        path: 'products',
        component: ProductsComponent
      },
      {
        path: 'categories-prod',
        component: CategoriesProdComponent
      },
      {
        path: 'manuf-prod',
        component: ManufProdComponent
      },
      {
        path: 'suppliers-prod',
        component: SuppliersProdComponent
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: '**',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
