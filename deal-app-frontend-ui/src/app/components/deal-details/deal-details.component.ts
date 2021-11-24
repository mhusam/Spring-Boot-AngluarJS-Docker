import { Component, OnInit } from '@angular/core';
import { DealService } from 'src/app/services/deal.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Deal } from 'src/app/models/deal.model';

@Component({
    selector: 'app-deal-details',
    templateUrl: './deal-details.component.html',
    styleUrls: ['./deal-details.component.css']
})
export class DealDetailsComponent implements OnInit {
    currentDeal: Deal = {
        uuid: '',
        dealId: '',
        fromCurrency: '',
        toCurrency: '',
        dateTime: '',
        amount: 0
    };
    message = '';

    constructor(
        private dealService: DealService,
        private route: ActivatedRoute,
        private router: Router) { }

    ngOnInit(): void {
        this.message = '';
        this.getDeal(this.route.snapshot.params.id);
    }

    getDeal(id: string): void {
        this.dealService.get(id)
            .subscribe(
                data => {
                    this.currentDeal = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    updateDeal(): void {
        this.message = '';

        this.dealService.update(this.currentDeal.uuid, this.currentDeal)
            .subscribe(
                response => {
                    console.log(response);
                    this.message = response.message ? response.message : 'This deal was updated successfully!';
                },
                error => {
                    console.log(error);
                });
    }

    deleteDeal(): void {
        this.dealService.delete(this.currentDeal.uuid)
            .subscribe(
                response => {
                    console.log(response);
                    this.router.navigate(['/deals']);
                },
                error => {
                    console.log(error);
                });
    }
}
