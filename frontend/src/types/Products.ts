export interface Product {
    productId: number,
    productName: String,
    image: string,
    description: string,
    quantity: number,
    price: number,
    discount: number,
    specialPrice: number
}


export interface ProductResponse {
    content: Product[];
  }