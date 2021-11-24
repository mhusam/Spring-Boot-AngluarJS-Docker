import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

// Datepicker module
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddDealComponent } from './components/add-deal/add-deal.component';
import { DealDetailsComponent } from './components/deal-details/deal-details.component';
import { DealsListComponent } from './components/deals-list/deals-list.component';

@NgModule({
    declarations: [
        AppComponent,
        AddDealComponent,
        DealDetailsComponent,
        DealsListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        BsDatepickerModule.forRoot() 
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
