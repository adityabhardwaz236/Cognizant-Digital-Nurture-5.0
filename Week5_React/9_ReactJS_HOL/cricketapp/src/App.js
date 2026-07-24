import React from "react";
import ListofPlayers from "./ListofPlayers";
import IndianPlayers from "./IndianPlayers";

function App() {

    const flag = true;
    // const flag = false;

    if (flag) {
        return <ListofPlayers />;
    }
    else {
        return <IndianPlayers />;
    }

}

export default App;