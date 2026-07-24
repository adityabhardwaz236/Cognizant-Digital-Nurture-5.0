import React from "react";

function EvenPlayers(props) {

    return (
        <div>
            <h2>Even Team Players</h2>

            <ul>
                <li>Second: {props.second}</li>
                <li>Fourth: {props.fourth}</li>
                <li>Sixth: {props.sixth}</li>
            </ul>
        </div>
    );
}

export default EvenPlayers;