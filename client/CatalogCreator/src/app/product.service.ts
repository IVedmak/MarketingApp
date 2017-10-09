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

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
