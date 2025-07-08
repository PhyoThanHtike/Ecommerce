import { useAllProducts } from "../../hooks/useProducts";
import ProductCard from "./ProductCard";

const Products = () => {
  const { data: allData, isLoading: isLoadingAll } = useAllProducts();

  const products = allData?.content || [];
  const islLoading = isLoadingAll;
  return (
    <div className="grid grid-cols-2 lg:grid-cols-3 mx-auto w-[90%]">
      {products.map((product: any) => (
        <ProductCard
        image={product.image}
        productName={product.productName}
        description={product.description}
        price={product.price}
      />
      ))}
    </div>
  );
};

export default Products;


        // <div key={product.productId} className="border p-4 rounded shadow">
        //   <img
        //     src={product.image}
        //     alt={product.productName}
        //     className="h-40 object-cover w-full mb-2"
        //   />
        //   <h2 className="font-semibold">{product.productName}</h2>
        //   <p>{product.description}</p>
        //   <p>Price: ${product.price}</p>
        //   {product.specialPrice > 0 && (
        //     <p className="text-green-600">
        //       Special Price: ${product.specialPrice}
        //     </p>
        //   )}
        // </div>