import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Logout() {

  sessionStorage.removeItem("Accesstoken");
  localStorage.removeItem("Accesstoken");

  const nav = useNavigate();
  useEffect(() => nav("/"));
}