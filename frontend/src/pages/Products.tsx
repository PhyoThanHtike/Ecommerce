import { useAllProducts } from "../hooks/useProducts"


const Products = () => {

    const{
        data: allData,
        isLoading: isLoadingAll
    } = useAllProducts();

    const products = allData?.content || [];
    const islLoading = isLoadingAll;
  return (
    <div>
        {products.map((product: any) => (
            <div key={product.productId} className="border p-4 rounded shadow">
              <img
                src={product.image}
                alt={product.productName}
                className="h-40 object-cover w-full mb-2"
              />
              <h2 className="font-semibold">{product.productName}</h2>
              <p>{product.description}</p>
              <p>Price: ${product.price}</p>
              {product.specialPrice > 0 && (
                <p className="text-green-600">
                  Special Price: ${product.specialPrice}
                </p>
              )}
            </div>
          ))}
    </div>
  )
}

export default Products