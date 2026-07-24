import React from "react";
import OddPlayers from "./OddPlayers";
import EvenPlayers from "./EvenPlayers";

function IndianPlayers() {

    const IndianTeam = [
        "Virat",
        "Rohit",
        "Gill",
        "Rahul",
        "Pant",
        "Hardik"
    ];

    const [
        first,
        second,
        third,
        fourth,
        fifth,
        sixth
    ] = IndianTeam;

    const T20Players = [
        "Virat",
        "Rohit",
        "Hardik"
    ];

    const RanjiPlayers = [
        "Pujara",
        "Rahane",
        "Sarfaraz"
    ];

    const mergedPlayers = [
        ...T20Players,
        ...RanjiPlayers
    ];

    return (
        <div>

            <OddPlayers
                first={first}
                third={third}
                fifth={fifth}
            />

            <EvenPlayers
                second={second}
                fourth={fourth}
                sixth={sixth}
            />

            <hr />

            <h2>List of Indian Players Merged</h2>

            <ul>
                {
                    mergedPlayers.map((player, index) => (
                        <li key={index}>
                            {player}
                        </li>
                    ))
                }
            </ul>

        </div>
    );
}

export default IndianPlayers;