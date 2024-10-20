import { useState } from "react";
import SkuTable from "../SkuTable/SkuTable";

export default function Table({ products, loading }) {
  const [selectedSkuList, setSelectedSkuList] = useState(null);

  const handleSkuList = (skuList) => {
    setSelectedSkuList(skuList); 
  };

  return (
    <div className="mt-2">
      {selectedSkuList ? ( 
        <SkuTable skuList={selectedSkuList} setSelectedSkuList={setSelectedSkuList} />
      ) : (
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>id</th>
              <th>Название</th>
              <th>Описание</th>
              <th>Создан</th>
              <th>Все SKU</th>
            </tr>
          </thead>
          <tbody>
            {!loading ? (
              products.map((product) => (
                <tr key={product.unique_id} >
                  <td>{product.id}</td>
                  <td>{product.name}</td>
                  <td>{product.description}</td>
                  <td>{product.createdAt}</td>
                  <td>
                    <button
                      type="button"
                      className="btn btn-success"
                      onClick={() => handleSkuList(product.skuList)}
                    >
                      Показать SKU
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5">Loading...</td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
}
