namespace ejemplo_eb.Manufacturing.Interfaces.REST.Resources
{
    public record CreateBillOfMaterialsItemResource(
       int BillOfMaterialsId,
       string ItemProductNumber,  // Se recibe como string (Guid)
       int BatchId,
       int RequiredQuantity,
       DateTime ScheduledStartAt,
       DateTime RequiredAt
   );
}
