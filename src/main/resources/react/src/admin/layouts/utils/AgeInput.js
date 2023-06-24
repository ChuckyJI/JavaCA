import { useState } from "react";

export const AgeInput = () => {
  const [isSelectMode, setIsSelectMode] = useState(false);
  const [age, setAge] = useState("");

  const handleInputChange = (e) => {
    setAge(e.target.value);
  };

  const toggleSelectMode = () => {
    setIsSelectMode(!isSelectMode);
    setAge("");
  };

  const handleSelectChange = (e) => {
    setAge(e.target.value);
  };

  return (
    <div className="form-group mt-1">
      <label>Age:</label>
      {isSelectMode ? (
        <select
          className="form-select"
          name="age"
          value={age}
          onChange={handleSelectChange}
          onBlur={toggleSelectMode}
        >
          <option value="">Select Age</option>
          {Array.from({ length: 71 }, (_, index) => (
            <option key={index + 12} value={index + 12}>
              {index + 12}
            </option>
          ))}
        </select>
      ) : (
        <input
          type="text"
          className="form-control"
          name="age"
          value={age}
          onChange={handleInputChange}
          onFocus={toggleSelectMode}
          placeholder="Select or Enter"
        />
      )}
    </div>
  );
};
