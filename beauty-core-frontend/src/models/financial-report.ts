export type TotalProfitInLiveDTO = {
  pix: number;
  cash: number;
  debit: number;
  credit: number;
  totalProfitInLive: number;
}

export type ProfessionalTotalProfitInLive = {
  pix: number;
  cash: number;
  debit: number;
  credit: number;
  totalProfit: number;
}

export type TotalProfitFilteredRequest = {
  start: string;
  end: string;
}

export type DailyProfitDTO = {
  date: string;
  total: number;
}

export type TotalProfitFilteredResponse = {
  list: DailyProfitDTO[];
  totalCalculated: number;
}