import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Asset } from './Asset';
import { AssetService } from './Asset.service';
import { NgForm } from '@angular/forms';
import { Employee } from './Employee';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public assets: Asset[] | undefined;
  public employees : Employee[] | undefined;
  public deleteAssets: Asset | undefined; 
  public editAssets : Asset | undefined;
  public historeis : Asset | undefined;
  public empId : String | undefined;
  constructor(private assetService : AssetService ){
  }
  
  ngOnInit() {
      this.getAssets();
  }

  public getAssets(): void{
    this.assetService.getAsset().subscribe(
      (response : Asset[])=> { 
        this.assets = response;
      },
      (error: HttpErrorResponse)=> { 
        alert(error.message);
      }
    )
  }
  public history(ID : String | undefined){
    if(ID!== null){
    this.assetService.getHistory(ID).subscribe(
      (response : Employee[])=> { 
        this.employees = response;
        if(this.employees.length!=0){
        for(let i=0;i<this.employees.length;i++){
        if(this.empId == undefined){
          this.empId = "";
        }  
        this.empId = this.empId+" , "+this.employees[i].employeeCode;
        }
        }
      },
      (error: HttpErrorResponse)=> { 
        alert(error.message);
      }
    )   
    }
  }
  public clearID(){
    this.empId="";
  }
  public addAssets(addForm : NgForm): void{
    document.getElementById("add-employee-form")?.click();
    this.assetService.addAsset(addForm.value).subscribe(
      (response : Asset) =>{
        this.getAssets();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
  public updateAssetTrue(addForm : NgForm,id : String | undefined): void{
    this.assetService.onEditAssetTrue(id , addForm.value).subscribe(
      (response : Asset) =>{
        this.getAssets();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public updateAssetFalse(id : String | undefined,emp : String | undefined): void{
    this.assetService.onEditAssetFalse(id , emp).subscribe(
      (response : Asset) =>{
        this.getAssets();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deleteAsset(ID : String|undefined): void{
    if(ID!=null){
    this.assetService.deleteAsset(ID).subscribe(
      (response: void) => {
        console.log(response);
        this.getAssets();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    }
  }
  public onOpenModal(mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addAssetModal');
    }
    container?.appendChild(button);
     button.click();
  }
  public onOpenModalOp(asset: Asset , mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'edit') {
      this.editAssets = asset;
      button.setAttribute('data-target', '#updateAssetModal');
    }
    if (mode === 'delete') {
      this.deleteAssets = asset;
      button.setAttribute('data-target', '#deleteAssetModal');
    }
    if(mode === 'History'){
      this.historeis = asset;
      this.history(this.historeis.id);
      button.setAttribute('data-target', '#HistoryAssetModal');
    }
    container?.appendChild(button);
     button.click();
  }
  public searchAsset( name: String | undefined){
    if(name!=''){
    this.assetService.searchAsset(name).subscribe(
      (response : Asset[])=> { 
        this.assets = response;
      },
      (error: HttpErrorResponse)=> { 
        alert(error.message);
      }
    )
    }
    else{
      this.getAssets();
    }  
  }

}
