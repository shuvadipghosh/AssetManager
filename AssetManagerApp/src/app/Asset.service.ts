import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Asset } from './Asset';
import { Employee } from './Employee';

@Injectable({providedIn: 'root'})
export class AssetService { 
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getAsset(): Observable<Asset[]>{
        return this.http.get<Asset[]>(`${this.apiServerUrl}/Asset/all`);
    }
    public addAsset(asset : Asset): Observable<Asset>{
        return this.http.post<Asset>(`${this.apiServerUrl}/Asset/addAsset`,asset);
    }
    public deleteAsset(id : String): Observable<void>{
        return this.http.delete<void>(`${this.apiServerUrl}/Asset/deleteAsset/${id}`);
    }
    public searchAsset(name : String | undefined){
        return this.http.get<Asset[]>(`${this.apiServerUrl}/Asset/findAsset/${name}`);
    }
    public onEditAssetTrue(id : String | undefined , asset:  Asset | undefined): Observable<Asset>{
        return this.http.put<Asset>(`${this.apiServerUrl}/Asset/updateAsset/${id}/${asset?.emp}`,asset);
    }
    public onEditAssetFalse(id : String | undefined, emp: String | undefined):Observable<Asset>{
        return this.http.put<Asset>(`${this.apiServerUrl}/Asset/updateAssetEmployee/${id}/${emp}`,id);
    }
    public getHistory(id : String | undefined ,): Observable<Employee[]>{
        return this.http.get<Employee[]>(`${this.apiServerUrl}/Asset/assetHistory/${id}`);
    }
}