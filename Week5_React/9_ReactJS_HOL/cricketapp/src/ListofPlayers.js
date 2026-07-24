import React from "react";

function ListofPlayers() {

    const players = [
        { name: "Virat", score: 90 },
        { name: "Rohit", score: 82 },
        { name: "Gill", score: 68 },
        { name: "Rahul", score: 74 },
        { name: "Pant", score: 66 },
        { name: "Hardik", score: 88 },
        { name: "Jadeja", score: 59 },
        { name: "Ashwin", score: 72 },
        { name: "Bumrah", score: 95 },
        { name: "Shami", score: 61 },
        { name: "Siraj", score: 77 }
    ];

    let players70 = [];

    players.map((player) => {
        if (player.score < 70) {
            players70.push(player);
        }
        return null;
    });

    return (
        <div>

            <h2>List of Players</h2>

            <ul>
                {
                    players.map((player, index) => (
                        <li key={index}>
                            {player.name} {player.score}
                        </li>
                    ))
                }
            </ul>

            <hr />

            <h2>List of Players having Scores Less than 70</h2>

            <ul>
                {
                    players70.map((player, index) => (
                        <li key={index}>
                            {player.name} {player.score}
                        </li>
                    ))
                }
            </ul>

        </div>
    );
}

export default ListofPlayers;