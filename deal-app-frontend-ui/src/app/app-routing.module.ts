import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DealsListComponent } from './components/deals-list/deals-list.component';
import { DealDetailsComponent } from './components/deal-details/deal-details.component';
import { AddDealComponent } from './components/add-deal/add-deal.component';

const routes: Routes = [
    { path: '', redirectTo: 'deals', pathMatch: 'full' },
    { path: 'deals', component: DealsListComponent },
    { path: 'deals/:id', component: DealDetailsComponent },
    { path: 'add', component: AddDealComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
