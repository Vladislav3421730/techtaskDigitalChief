export default function SkuTable({ skuList, setSelectedSkuList }) {
    return (
      <div>
        <h3>Список SKU</h3>
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th scope="col">Цвет</th>
              <th scope="col">Цена</th>
              <th scope="col">Размер</th>
            </tr>
          </thead>
          <tbody>
            {skuList.map((sku, index) => (
              <tr key={index}>
                <td>{sku.color}</td>
                <td>{sku.cost}</td>
                <td>{sku.size}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <button className="btn btn-success" onClick={() => setSelectedSkuList(null)}>
          Назад к списку продуктов
        </button>
      </div>
    );
  }
  