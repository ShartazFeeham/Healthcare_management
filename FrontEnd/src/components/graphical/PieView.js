import { Cell, Pie, PieChart } from "recharts"
import PieItem from "./PieItem"
import styles from './PieView.module.css'
import { Box, IconButton, useTheme, Typography } from "@mui/material";

export default function PieView({ title, data, description, colors, size }) {
    const theme = useTheme();
    const mode = theme.palette.mode;

    if (data === null || data.length === 0) return (<><div className={styles.container}><h3>Not enough data to make a Pie chart on <br></br> {title}</h3></div></>)
    return (<div className="graph-item">
        <div className={styles.container}>
            <div className={styles.description}>
                {description}
            </div>
            <div className={styles.innercont}>
                <div className={styles.visual}>
                    <PieChart width={25 * size} height={25 * size}>
                        <Pie data={data} cx="50%" cy="50%" outerRadius={8 * size} label>
                            {
                                data.map((entry, index) => (
                                    <Cell
                                        key={`cell-${index}`}
                                        fill={colors[index]}
                                    />
                                ))
                            }
                        </Pie>
                    </PieChart>
                </div>
                <div className={styles.title}>
                    {title}
                </div>
                <div style={{ display: 'flex', flexWrap: 'wrap', alignContent: 'justify' }}>
                    {data.map((entry, index) => (
                        <PieItem
                            key={`cell-${index}`}
                            entry={entry}
                            color={mode === 'dark' ? 'black' : 'white'}
                            bgcolor={colors[index]}
                        />
                    ))}
                </div>
            </div>
            <br></br>

        </div>
    </div>)
}

{/* <PieView title={'Calories burned from diet'} data={weeklyJson} colors={randomColorArray(weeklyJson.length, graphColor)} size={10} description={type + ' view of your daily intake calories. Each block of the graph shows amount of calories you ate a day.'} />
         */}