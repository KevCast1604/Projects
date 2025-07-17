namespace ejemplo_eb.Manufacturing.Domain.Model.Commands
{
    public record CreateBillOfMaterialsItemCommand (
    int BillOfMaterialsId,
    Guid ItemProductNumber,
    int BatchId,
    int RequiredQuantity,
    DateTime ScheduledStartAt,
    DateTime RequiredAt
    );
}