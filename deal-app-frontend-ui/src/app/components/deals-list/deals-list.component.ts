import { Component, OnInit } from '@angular/core';
import { Deal } from 'src/app/models/deal.model';
import { DealService } from 'src/app/services/deal.service';

@Component({
    selector: 'app-deals-list',
    templateUrl: './deals-list.component.html',
    styleUrls: ['./deals-list.component.css']
})
export class DealsListComponent implements OnInit {
    deals?: Deal[];
    currentDeal?: Deal;
    currentIndex = -1;
    dealId = '';

    constructor(private dealService: DealService) { }

    ngOnInit(): void {
        this.retrieveDeals();
    }

    retrieveDeals(): void {
        this.dealService.getAll()
            .subscribe(
                data => {
                    this.deals = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    refreshList(): void {
        this.retrieveDeals();
        this.currentDeal = undefined;
        this.currentIndex = -1;
    }

    setActiveDeal(deal: Deal, index: number): void {
        this.currentDeal = deal;
        this.currentIndex = index;
    }

    removeAllDeals(): void {
        this.dealService.deleteAll()
            .subscribe(
                response => {
                    console.log(response);
                    this.refreshList();
                },
                error => {
                    console.log(error);
                });
    }

    searchDealId(): void {
        this.currentDeal = undefined;
        this.currentIndex = -1;

        this.dealService.findByDealId(this.dealId)
            .subscribe(
                data => {
                    this.deals = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

}
