import axios from "axios";
import { useNavigate } from "react-router-dom";


export const AxiosInstance = axios.create({
        baseURL: 'http://localhost:8080',
        headers: {
            'Content-Type': 'application/json'
        }
    })



export default class ApiService{
    

    static getHeader() {
        const token = localStorage.getItem("jwt");
        return{
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    }

    /* --AUTH-- */

    static async regiterUser(registration){
        return await AxiosInstance.post(`/auth/sign-up`, registration);
    }

    static async loginUser(loginDetails){
        console.log(loginDetails);
        return await AxiosInstance.post(`auth/sign-in`, loginDetails);
    }

    /*  --USERS--*/

    static async getAllUsers(){
        const response = await AxiosInstance.get(`$/api/users`, {
            headers: this.getHeader()
        });
        return response.data;   
    }

    static async getUserById(userId){
        const response = await AxiosInstance.get(`/api/users/${userId}`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    /** --BOOKS-- */

    static async getAllBooks(){
        return AxiosInstance.get("/api/book", {
            headers: this.getHeader()
        });
    }

     static async addBook(bookDto){
        return await AxiosInstance.post(`/api/book`, bookDto,
            {
                headers: {
                    ...this.getHeader()
                }   
            });
     }

     static async deleteBook(bookId){
        return await AxiosInstance.delete(`/api/book/${bookId}`, {
            headers: this.getHeader()
        })
     }

     static async updateBook(bookId, bookDetails){
        return await AxiosInstance.put(`/api/book/${bookId}`, bookDetails, {
            headers: this.getHeader()
        });
     }

     static async search(title){
        return await AxiosInstance.get(`/api/book/search?title=${title}`,
        {
            headers: this.getHeader()
        })
    }

     /** --ATTACHMENT-- */

     static async upload(attachment){
        return await AxiosInstance.post(`/api/attachment/upload`, attachment, {
            headers: {
                ...this.getHeader(),
                "Content-Type": "multipart/form-data"
            }
     })
    }

    static async download(attachmentId){
        return await AxiosInstance.get(`/api/attachment/${attachmentId}`, {
            headers: this.getHeader()
        })
    }

     /** ------ */

     static async isAuthenticated(){
        const token = localStorage.getItem('token');
        return !!token;         
     }
     static async isAdmin(){
        const role = localStorage.getItem('role');
        return role === 'ADMIN';
     }

     static async isUser(){
        const role = localStorage.getItem('role');
        return role === 'USER';
     }


}