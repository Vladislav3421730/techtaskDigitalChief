
export async function fetchProducts(setProducts, setLoading,setMessage) {
    setLoading(true);
    try {
        const response = await fetch("http://localhost:8080/api/products/findAll");
        if (!response.ok) {
            setLoading(false);
            const error=await response.json()
            setMessage(error.message)
            console.log(error)
        }
        const data = await response.json();
        setProducts(data);
    } catch (error) {
        console.log(error);
    }
    setLoading(false);
}

export async function AddData(setLoading,setMessage) {
    setLoading(true);
    try {
        const response = await fetch("http://localhost:8080/api/products/addData");
        if (!response.ok) {
            setLoading(false);
            const error=await response.json()
            setMessage(error.message)
            console.log(error)
        }
    } catch (error) {
        console.log(error);
    }
    setLoading(false);
}

export async function SearchData(setProducts,setLoading,search,setMessage){
    setLoading(true);
    try {
        const response = await fetch(`http://localhost:8080/api/products/findBy/${search}`);
        if (!response.ok) {
            setLoading(false);
            const error=await response.json()
            setMessage(error.message)
            console.log(error)
        }
        const data = await response.json();
        setProducts(data);
    } catch (error) {
        console.log(error);
    }
    setLoading(false);
}


