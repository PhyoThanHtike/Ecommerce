import { useQuery } from '@tanstack/react-query';
import type { ProductResponse } from '../types/Products';
import { getAllProducts, getProductByCategory } from '../apiEndpoints/ProductsEndPoint';

export const useAllProducts = () =>{
    return useQuery<ProductResponse, Error>({
        queryKey: ['products'],
        queryFn: getAllProducts
    })
}
    

export const useProductsByCategory = (categoryId: number) =>{
    return useQuery<ProductResponse,Error>({
        queryKey: ['products', 'category', categoryId],
        queryFn: ()=> getProductByCategory(categoryId)
    });
}
    // useQuery<ProductResponse, Error>(
    //   ['products', 'category', categoryId],
    //   () => getProductByCategory(categoryId),
    // );
