import { Route, Routes, useSearchParams } from "react-router-dom";
import List from "../components/board/List";
import Index from "../components";
import Add from "../components/board/Add";
import StudyParam from "../study/StudyParam";
import Login from "../components/member/Login";

export default function AppRoutes() { // 이렇게 하면 맨 밑에 export default 안 써줘도 됨됨

  return(
    <>
      <Routes>
        {/* path="경로" element={해당 경로일 때 이동하고싶은 컴포넌트} */}
        <Route path="/" element={<Index/>}></Route>
        
        {/* 아래는 /notice/list, /notice/add 임 */}
        <Route path="/notice/">
          <Route path="list" element={<List/>}></Route> 
          <Route path="add" element={<Add/>}></Route>
        </Route>

        <Route path="/member/">
          <Route path="login" element={<Login/>}></Route>
        </Route>

        {/* element나 Component나 같은 역할 */}
        <Route path="/study/param"  element={<StudyParam/>}></Route>
        <Route path="/study/param/:num/:name"  element={<StudyParam/>}></Route>

      </Routes>
    </>
  )
}