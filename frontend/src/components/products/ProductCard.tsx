import React from "react";

type ProductCardProps = {
  image: string;
  productName: string;
  description: string;
  price: number;
};

const ProductCard: React.FC<ProductCardProps> = ({
  image,
  productName,
  description,
  price,
}) => {
  return (
    <div className="card bg-base-100 w-60 md:w-96 shadow-sm my-8">
      <figure>
        <img src="https://img.daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.webp" alt={productName} />
      </figure>
      <div className="card-body">
        <div className="flex flex-col justify-start">
          <h2 className="card-title text-sm md:text-lg">{productName}</h2>
          <p className="text-sm md:text-base">$ {price}</p>
        </div>
        <p className="text-xs md:text-base">{description}</p>
        <div className="card-actions justify-end">
          <button className="btn btn-primary">Buy Now</button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;

