import React from "react";
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles({
  root: {
    minWidth: 500,
    maxWidth: 1000,
    marginTop: 90,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export default function InventoryPage() {
  const classes = useStyles();

  return <div>
    <Box className={classes.root}>
      <Typography h2>Inventory</Typography>
    </Box>
  </div>
}