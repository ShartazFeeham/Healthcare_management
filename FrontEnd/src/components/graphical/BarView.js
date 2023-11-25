import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import styles from '../Graphical/PieView.module.css'
import PieItem from './PieItem';
import { useTheme } from "@mui/material";

export default function BarView({ title, data, colors, description, requiredAmount }) {

    const theme = useTheme();
    const mode = theme.palette.mode;

    const para1 = 'required'
    const para2 = 'value'
    for (let i = 0; i < data.length; i++) {
        data[i] = {
            name: data[i].name,
            value: data[i].value,
            required: Math.max(0, (requiredAmount - data[i].value))
        }
    }
    if (data === null || data.length === 0) return (<><div className={styles.container}><h3>Not enough data to make a Bar chart on <br></br> {title}</h3></div></>)
    return (<>
        <div className={styles.container}>
            <div className={styles.description}>
                {description}
            </div>
            <div className={styles.innercont}>
                <div className={styles.visual}>
                    <ResponsiveContainer width={250} height={250}>
                        <BarChart
                            data={data}
                            margin={{
                                right: 10,
                            }}>
                            <CartesianGrid strokeDasharray="2 2" />
                            <XAxis dataKey="name" />
                            <YAxis />
                            <Tooltip />
                            <Legend />
                            <Bar dataKey={para2} stackId="a" fill={colors[0]} />
                            {requiredAmount != null && requiredAmount > 0 ?
                                <Bar dataKey={para1} stackId="a" fill="#8884d8" /> :
                                ""}
                        </BarChart>
                    </ResponsiveContainer>
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
        </div>
    </>)

}

{/* <BarView title={'Calories burned from diet'} data={weeklyJson} colors={randomColorArray(weeklyJson.length, graphColor)} description={type + ' view of your daily intake calories. Each block of the graph shows amount of calories you ate a day.'} />
<BarView title={'Water intakes'} data={water} colors={randomColorArray(water.length, graphColor)} requiredAmount={4.0} description={type + ' view of your daily intake calories. Each block of the graph shows amount of calories you ate a day.'} />
         */}