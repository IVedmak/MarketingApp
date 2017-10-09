import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { ProductItem } from './product-item';

@Injectable()
export class ProductService {

  constructor(private http: Http) { }

  getProductItems(): Promise<ProductItem[]> {
    return this.http.get("http://localhost:8080/convertedPosts").toPromise()
      .then(response => response.json() as ProductItem[])
      .catch(this.handleError);
  }

  uploadProductFeed(feedName:string,productItems:ProductItem[]):Promise<boolean>{
    const url = 'http://localhost:8080/uploadProductFeed/' + feedName;
    return this.http.post(url,productItems).toPromise()
    .then(response => response.json() as boolean)
    .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
