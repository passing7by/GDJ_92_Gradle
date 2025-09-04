import { useLocation, useParams } from "react-router-dom"
import { Route, Routes, useSearchParams } from "react-router-dom";

export default function StudyParam() {
  // 1. useSearchParam 사용 - 값이 url에에 쿼리스트링 형식으로 보이는 경우에 사용(Restful X)
  const [searchParams, setSearchParams] = useSearchParams();
  const num = searchParams.get('num');
  const name = searchParams.get('name');
  console.log("useSearchParam: ", num, name);

  // 2. useParam 사용 - 값이 url에 경로 형식으로 보이는 경우에 사용(Restful O)
  // AppRoutes.jsx의 경로를 다음과 같은 형식으로 작성해야 함
  // path="/study/param/:num/:name"
  console.log("useParam: ", useParams().num, useParams().name);
  // 이렇게도 사용 가능
  /*
  const {num, name} = useParams();
  console.log("useParam: ", num, name);
  */

  // 3. useLocation
  const loc = useLocation();

  // 쿼리 스트링을 가져오는 방법
  /*
  console.log(loc);
  console.log(loc.search, " ", loc.pathname);

  const us = new URLSearchParams(loc.search);
  console.log(us.get("num"), us.get("name"));
  */

  // state를 가져오는 방법
  if(loc.state != null) {
    console.log(loc.state.age, loc.state.user);
  }
  
  return(
    <>
      <h1>Study Param</h1>
    </>
  )
}