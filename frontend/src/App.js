import { useState, useEffect } from "react";
import Table from "./components/Table/Table";
import { AddData, fetchProducts, SearchData } from "./api";

function App() {

  const [products, setProdutcs] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message,setMessage]=useState(null)

  useEffect(() => {
    fetchProducts(setProdutcs, setLoading,setMessage);
  }, []);

  const handleAddData =  () => {
    AddData(setLoading,setMessage);
    fetchProducts(setProdutcs, setLoading,setMessage);
  };


  return (
    <div className="container mt-4">
      <button type="button" className="btn btn-primary" onClick={handleAddData}>Добавить данные в индекс (вызов службы) </button>
      
      <div className="input-group mb-3 mt-2">
        <input id="inputsearch" type="text" className="form-control" placeholder="Строка для поиска по названию, описанию, цвету" />
        <div className="input-group-append">
          <button className="btn btn-secondary" onClick={()=> SearchData(setProdutcs, setLoading,  document.getElementById("inputsearch").value,setMessage)} type="button">Найти</button>
        </div>
      </div>
    
      <Table products={products} loading={loading}></Table>
      {message && <p style={{ color: 'red' }}>{message}</p>}
    </div>
  );
}

export default App;
