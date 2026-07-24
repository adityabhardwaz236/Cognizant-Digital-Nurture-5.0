import React from "react";

function OddPlayers(props) {

    return (
        <div>
            <h2>Odd Team Players</h2>

            <ul>
                <li>First: {props.first}</li>
                <li>Third: {props.third}</li>
                <li>Fifth: {props.fifth}</li>
            </ul>
            <hr></hr>
        </div>
    );
}

export default OddPlayers;