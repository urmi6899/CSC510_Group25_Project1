import React from "react";
import InventoryForm from "./inventory-form";
import InventoryTable from "./inventory-table";
import Grid from "@material-ui/core/Grid";

export default function InventoryPage({currentUser}) {

  const [updateInventory, setUpdateInventory] = React.useState(false);
  
  const handleUpdateInventory = () => {
    setUpdateInventory(!updateInventory);
  }

  return <div>
    <Grid
      container
      spacing={3}
      direction="column"
      alignItems="center"
      justifyContent="center"
      style={{ minHeight: '100vh' }}
    >
      <Grid item xs={3}>
        <InventoryForm currentUser={currentUser} onFormSubmit={handleUpdateInventory} />
      </Grid>
      <Grid item md={12}>
        <InventoryTable updateInventory={updateInventory} currentUser={currentUser} />
      </Grid>
    </Grid>
  </div>
}