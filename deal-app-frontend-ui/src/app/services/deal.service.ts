import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deal } from '../models/deal.model';

const baseUrl = 'http://localhost:8088/api/v1/deal';

@Injectable({
    providedIn: 'root'
})
export class DealService {

    constructor(private http: HttpClient) { }

    getAll(): Observable<Deal[]> {
        return this.http.get<Deal[]>(baseUrl);
    }

    get(id: any): Observable<Deal> {
        return this.http.get(`${baseUrl}/${id}`);
    }

    create(data: any): Observable<any> {
        return this.http.post(baseUrl, data);
    }

    update(id: any, data: any): Observable<any> {
        return this.http.put(`${baseUrl}/${id}`, data);
    }

    delete(id: any): Observable<any> {
        return this.http.delete(`${baseUrl}/${id}`);
    }

    deleteAll(): Observable<any> {
        return this.http.delete(baseUrl);
    }

    findByDealId(dealId: any): Observable<Deal[]> {
        return this.http.get<Deal[]>(`${baseUrl}?dealId=${dealId}`);
    }
}
