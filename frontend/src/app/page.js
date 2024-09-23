"use client";
import { useState } from "react";
import { FormControl, InputLabel, Select, MenuItem, Box } from "@mui/material";
import Grid from "@mui/material/Grid2";
import Chart from "react-apexcharts";

export default function Home() {
  const [year, setYear] = useState("2011");
  const [brand, setBrand] = useState({ brand: "", brandCode: "" });
  const [brands, setBrands] = useState([]);
  const [model, setModel] = useState({ model: "", modelCode: "" });
  const [models, setModels] = useState([]);
  const [insuranceList, setInsuranceList] = useState([]);

  const [chart, setChart] = useState({
    options: {
      chart: {
        id: "apexchart-example",
      },
      xaxis: {
        categories: insuranceList.map(
          (item) => `${item.year}-${item.month.toString().padStart(2, "0")}`
        ),
      },
    },
    series: [
      {
        name: "series-1",
        data: insuranceList.map((item) => item.tlPrice),
      },
    ],
  });

  const currentYear = new Date().getFullYear();
  const years = Array.from({ length: 15 }, (_, index) =>
    (currentYear - index).toString()
  );

  const handleYearChange = (e) => {
    setYear(e.target.value);
    setBrand({ brand: "", brandCode: "" });
    setModel({ model: "", modelCode: "" });
    fetchBrands(year);
  };

  const fetchBrands = async (year) => {
    const response = await fetch(
      `http://localhost:8080/api/vehicles/brands?year=${year}`
    );
    const data = await response.json();
    setBrands(data);
  };

  const handleBrandChange = (e) => {
    const selectedBrand = brands.find((b) => b.brand === e.target.value);
    setBrand(selectedBrand);
    setModel({ model: "", modelCode: "" });
    fetchModels(selectedBrand.brandCode, year);
  };

  const fetchModels = async (brandCode, year) => {
    const response = await fetch(
      `http://localhost:8080/api/vehicles/models?brandCode=${brandCode}&year=${year}`
    );
    const data = await response.json();
    setModels(data);
  };

  const handleModelChange = (e) => {
    const selectedModel = models.find((m) => m.model === e.target.value);
    setModel(selectedModel);
    fetchInsuranceList(selectedModel.modelCode, year);
  };

  const fetchInsuranceList = async (modelCode, year) => {
    const response = await fetch(
      `http://localhost:8080/api/insurances/${brand.brandCode}/${modelCode}/${year}`
    );
    const data = await response.json();
    data.sort((a, b) => {
      return new Date(a.year, a.month) - new Date(b.year, b.month);
    });
    setInsuranceList(data);
    setChart({
      options: {
        ...chart.options,
        xaxis: {
          categories: data.map(
            (item) => `${item.year}-${item.month.toString().padStart(2, "0")}`
          ),
          tickPlacement: "on",
          type: "x",
        },
      },
      series: [
        {
          name: "series-1",
          data: data.map((item) => item.tlPrice),
        },
      ],
    });
  };

  return (
    <Box
      sx={{
        minHeight: "100vh",
        display: "flex",
        flexDirection: "column",
        background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
        padding: 4,
      }}
    >
      <Grid container spacing={4}>
        {/* Left side: Dropdown lists */}
        <Grid item xs={12} md={4}>
          <Box
            sx={{
              backgroundColor: "rgba(255, 255, 255, 0.8)",
              borderRadius: 2,
              padding: 3,
              boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
            }}
          >
            <FormControl fullWidth sx={{ mb: 2 }}>
              <InputLabel id="year-label">Year</InputLabel>
              <Select
                labelId="year-label"
                value={year}
                label="Year"
                onChange={handleYearChange}
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                {years.map((y) => (
                  <MenuItem key={y} value={y}>
                    {y}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

            <FormControl fullWidth sx={{ mb: 2 }}>
              <InputLabel id="brand-label">Brand</InputLabel>
              <Select
                labelId="brand-label"
                value={brand.brand}
                label="Brand"
                onChange={handleBrandChange}
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                {brands.map((b) => (
                  <MenuItem key={b.brandCode} value={b.brand}>
                    {b.brand}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

            <FormControl fullWidth>
              <InputLabel id="model-label">Model</InputLabel>
              <Select
                labelId="model-label"
                value={model.model}
                label="Model"
                onChange={handleModelChange}
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                {models.map((m) => (
                  <MenuItem key={m.modelCode} value={m.model}>
                    {m.model}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </Box>
        </Grid>

        {/* Right side: Line Chart */}
        <Grid item xs={12} md={8}>
          <Box
            sx={{
              backgroundColor: "rgba(255, 255, 255, 0.8)",
              borderRadius: 2,
              padding: 3,
              boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
              height: "100%",
            }}
          >
            <Chart
              options={chart.options}
              series={chart.series}
              type="line"
              width={1000}
              height={320}
            />
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
}
