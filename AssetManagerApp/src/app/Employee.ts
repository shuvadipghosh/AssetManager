import { Asset } from "./Asset";

export interface Employee{
    ID : String;
    employeeCode : String;
    name : String;
    assets : Asset[];
}