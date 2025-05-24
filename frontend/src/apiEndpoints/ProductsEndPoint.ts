import axiosInstance from "../axiosAPI/axiosInstance";
// import { Product } from "../types/Products";
import type { ProductResponse } from "../types/Products";

export const getAllProducts = async (): Promise<ProductResponse> => {
  try {
    const response = await axiosInstance.get("/public/products");
    return response.data;
  } catch (err: any) {
    throw new Error(err?.response?.data?.message || "Failed to fetch products");
  }
};

export const getProductByCategory = async (
  categoryId: number
): Promise<ProductResponse> => {
  const response = await axiosInstance.get(
    `/public/categories/${categoryId}/products`
  );
  return response.data;
};

export const getProductsByKeyword = async (
  keyword: string
): Promise<ProductResponse> => {
  const res = await axiosInstance.get(`/public/products/keyword/${keyword}`);
  return res.data;
};
