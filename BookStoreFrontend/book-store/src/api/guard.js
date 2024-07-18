import { Component } from "react"
import { Navigate, useLocation, useNavigate } from "react-router-dom"
import ApiService from "./ApiService";

export const ProctedRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAuthenticated() ? (
        Component
    ) : (
        <Navigate to="/login" replace state={{from : location}}/>
    )
}

export const AdminAuthentication = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAdmin() ? (
        Component
    ) : (
        <Navigate to="/login" replace state={{from: location}}/>
    )
}