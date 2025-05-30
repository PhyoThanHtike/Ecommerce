import axios from "axios";

const axiosInstance = axios.create({
    baseURL: `${import.meta.env.VITE_BACK_END_URL}`,
    withCredentials: true
});

export default axiosInstance;